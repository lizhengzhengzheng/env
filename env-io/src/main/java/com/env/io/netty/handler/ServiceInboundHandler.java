package com.env.io.netty.handler;

import com.env.io.EnvIoApplication;
import com.env.io.common.DeviceRequest;
import com.env.io.netty.service.ServerService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServiceInboundHandler extends ChannelInboundHandlerAdapter {
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object obj) throws Exception {
		DeviceRequest msg = (DeviceRequest) obj;
		ServerService serverService = (ServerService)EnvIoApplication.getBean(msg.getType());
		serverService.main(msg, ctx.channel());
	}
}
