package com.env.io.netty.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.env.io.common.DeviceCommon;
import com.env.io.common.DeviceRequest;
import com.env.io.common.RedisColumRalation;
import com.env.io.common.RedisUtil;
import com.env.io.dao.MonitoringDataRepository;
import com.env.io.dao.SiteInfoRepository;
import com.env.io.dao.SiteYinziRepository;
import com.env.io.dao.YinziInfoRepository;
import com.env.io.entity.MonitoringData;
import com.env.io.entity.SiteInfo;
import com.env.io.entity.SiteYinzi;
import com.env.io.entity.YinziInfo;
import io.netty.channel.Channel;

/**
 * 监测数据
 * @author lizheng
 * @date 2020年3月28日
 *
 */
@Service("T002")
public class T002Service implements ServerService {
	
	@Autowired
	private RedisUtil redisUtil;
	
	@Resource
    RabbitTemplate rabbitTemplate;  //使用RabbitTemplate,这提供了接收/发送等等方法
	
	@Resource
	private SiteInfoRepository siteInfoRepository;
	
	@Resource
	private SiteYinziRepository siteYinziRepository;
	
	@Resource
	private YinziInfoRepository yinziInfoRepository;
	
	@Resource
	private MonitoringDataRepository monitoringDataRepository;

	@Override
	public void main(DeviceRequest msg, Channel channel) {
		// TODO Auto-generated method stub
		if(!DeviceCommon.isHeart(msg,channel)) {
			return;
		}else {
			String serialNum = System.currentTimeMillis() + msg.getId();
			SiteInfo siteInfo = siteInfoRepository.findByAccessCodeAndState(msg.getId(),0);
			MonitoringData monitoringData = new MonitoringData();
			Timestamp ts=new Timestamp(new Date().getTime());
			monitoringData.setSiteId(siteInfo.getId());
			monitoringData.setSerialNum(serialNum);
			monitoringData.setCreateAt(ts);
			@SuppressWarnings("unused")
			List<String> siteYinzi = new ArrayList<>();
			String str = (String) redisUtil.hget(msg.getId(), RedisColumRalation.siteYinzi);
			if(str == null) {
				List<SiteYinzi> a = siteYinziRepository.findBySiteIdAndState(siteInfo.getId(),0);
				StringBuffer str2 = new StringBuffer();
				for (SiteYinzi item : a) {
					YinziInfo y = yinziInfoRepository.findById(item.getYinziId()).get();
					str2.append(y.getYinziNum() + ",");
				}
				if(str2.length()>0) {
					str2.deleteCharAt(str2.length() - 1);
				}
				redisUtil.hset(msg.getId(), RedisColumRalation.siteYinzi, str2, 14400);
			}else {
				siteYinzi = Arrays.asList(str.split(","));
			}
			for (String key : msg.getMap().keySet()) {
//				if(!siteYinzi.contains(key)) {
//					DeviceCommon.responseErr(msg, channel, "The " +key+" does not exist");
//					return;
//				}
				int yinziId = 0;
				try {
					yinziId = (int) redisUtil.hget(String.valueOf(siteInfo.getProjectId()), RedisColumRalation.yinziNum);
				}catch(Exception e) {
					YinziInfo yinziInfo = yinziInfoRepository.findByYinziNumAndStateAndProjectId(key, 0, siteInfo.getProjectId());
					yinziId = yinziInfo.getId();
					redisUtil.hset(String.valueOf(siteInfo.getProjectId()), RedisColumRalation.yinziNum, String.valueOf(yinziId), 14400);
				}
				monitoringData.setYinziValue(Double.valueOf((String) msg.getMap().get(key)));
				monitoringData.setYinziId(yinziId);
				//将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
		        rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", monitoringData);
			}
			DeviceCommon.responseOk(msg, channel);
		}
	}

}
