package com.env.io.netty.handler;

import com.env.io.common.DeviceCommon;
import com.env.io.common.RedisUtil;
import com.env.io.common.SpringUtil;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * 心跳超时处理逻辑
 * @author 10441
 *
 */
public class HeartBeatServerHandler extends ChannelInboundHandlerAdapter {
	
	private int lossConnectCount = 0;
	
    private RedisUtil redisUtil = SpringUtil.getBean(RedisUtil.class);
	
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent)evt;
            if (event.state()== IdleState.READER_IDLE){
                lossConnectCount++;
                if (lossConnectCount>1){
                	Channel channel = ctx.channel();
                	String deviceId = DeviceCommon.getDevice(channel);
    				DeviceCommon.deviceChannelMap.remove(deviceId);
    				redisUtil.del(deviceId);
                    System.out.println("关闭不活跃通道:" + deviceId);
                    ctx.channel().close();
                }
            }
        }else {
            super.userEventTriggered(ctx,evt);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        lossConnectCount = 0;
        ctx.fireChannelRead(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

}
