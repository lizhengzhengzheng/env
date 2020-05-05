package com.env.io.netty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.env.io.common.DeviceCommon;
import com.env.io.common.DeviceRequest;
import com.env.io.common.GetIpUtil;
import com.env.io.common.RedisColumRalation;
import com.env.io.common.RedisUtil;
import io.netty.channel.Channel;

/**
 * 心跳
 * {"type":"T001","state":"3","id":"AGS-1501","map":{"state":"ok"}}$$
 * @author lizheng
 * @date 2020年3月27日
 *
 */
@Service("T001")
public class T001Service implements ServerService {
	
	@Autowired
	private RedisUtil redisUtil;
	
	@Value("${server.port}")
	private int port;

	@Override
	public void main(DeviceRequest msg, Channel channel) {
		// TODO Auto-generated method stub
		redisUtil.hset(msg.getId(), RedisColumRalation.state, msg.getMap().get(DeviceCommon.state));
		redisUtil.hset(msg.getId(), RedisColumRalation.address, GetIpUtil.getIpAddress() + ":" + port);
		DeviceCommon.deviceChannelMap.put(msg.getId(), channel);
		DeviceCommon.responseOk(msg, channel);
	}
	
}
