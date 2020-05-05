package com.env.io.netty.handler;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import net.bytebuddy.asm.Advice.This;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.annotation.PostConstruct;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.env.io.common.DeviceCommon;
import com.env.io.common.DeviceRequest;
import com.env.io.common.GetIpUtil;
import com.env.io.common.RedisUtil;
import com.env.io.common.ServerResponse;
import com.env.io.dao.SiteInfoRepository;
import com.env.io.entity.SiteInfo;

/**
 * 设备验证逻辑
 * @author 10441
 *
 */
@Component
public class DeviceValidationHandler extends ChannelInboundHandlerAdapter{
	
	@Autowired
	private RedisUtil redisUtil;
	
	@Autowired
	private SiteInfoRepository siteInfoRepository;
			
	private static final Logger LOG = LoggerFactory.getLogger(This.class);
	
	private static DeviceValidationHandler deviceValidationHandler;
	
	@PostConstruct
    public void init() {
		deviceValidationHandler = this;
    }
		
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		//数据解析
		DeviceRequest obj = null;
		Channel channel = ctx.channel();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ServerResponse serverResponse = new ServerResponse();
		try {
			obj = JSONObject.parseObject(msg.toString(), DeviceRequest.class);
		}catch(Exception e) {
			LOG.error("json解析失败:" + msg.toString());
			LOG.error(e.toString());
			serverResponse.setStatus(DeviceCommon.ERR);
			serverResponse.setTime(sf.format(new Date()));
			serverResponse.setMsg("Data parsing failure");
			channel.writeAndFlush(Unpooled.copiedBuffer(JSON.toJSONString(serverResponse), CharsetUtil.UTF_8));
			return;
		}
		
		//ip识别
		String clientIP = GetIpUtil.getIpAddress();
		List<SiteInfo> list = deviceValidationHandler.siteInfoRepository.findByNetIpAndState(clientIP, 0);
		if(list.size() == 0) {
			serverResponse.setStatus(DeviceCommon.ERR);
			serverResponse.setTime(sf.format(new Date()));
			serverResponse.setMsg("Illegal IP:" + clientIP);
			channel.writeAndFlush(Unpooled.copiedBuffer(JSON.toJSONString(serverResponse), CharsetUtil.UTF_8));
			return;
		}
		
		//设备识别
		SiteInfo site = deviceValidationHandler.siteInfoRepository.findByAccessCodeAndState(obj.getId(), 0);
		if(site == null) {
			serverResponse.setStatus(DeviceCommon.ERR);
			serverResponse.setTime(sf.format(new Date()));
			serverResponse.setMsg("Illegal device");
			channel.writeAndFlush(Unpooled.copiedBuffer(JSON.toJSONString(serverResponse), CharsetUtil.UTF_8));
			return;
		}
		
		//主动相应or被动回应？
		int status = obj.getState();
		boolean isResponse = status == DeviceCommon.OK || status == DeviceCommon.ERR;
		if (isResponse) {
//			String channelId = channel.id().asLongText();
			deviceValidationHandler.redisUtil.set(obj.getCilentId()==null?"a":obj.getCilentId(), obj, 10);
		} else {
			// 转到下一个channelHandler
			ctx.fireChannelRead(obj);
		}
	}
}
