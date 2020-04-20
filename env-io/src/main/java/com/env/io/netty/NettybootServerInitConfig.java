package com.env.io.netty;

import java.util.concurrent.TimeUnit;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import com.env.io.netty.handler.DeviceValidationHandler;
import com.env.io.netty.handler.HeartBeatServerHandler;
import com.env.io.netty.handler.ServiceInboundHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.timeout.IdleStateHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * netty启动
 * @author lizheng
 * @date 2020年4月9日
 *
 */
@Component//当成组件处理
@Order(value = 1)//这里表示启动顺序
public class NettybootServerInitConfig implements CommandLineRunner {
	
	@Value("${netty.port}")
	private int port;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	 
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private Channel channel;
 
    public void start() {
        bossGroup = new NioEventLoopGroup(); //监听线程组，监听客户请求
        workerGroup = new NioEventLoopGroup();//处理客户端相关操作线程组，负责处理与客户端的数据通讯
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //设置监听组，线程组，初始化器
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new ServerInitialzer());
            logger.info("Netty Server start");
            //绑定端口号
            ChannelFuture f = serverBootstrap.bind(port).sync();
            channel = f.channel().closeFuture().sync().channel();//这里绑定端口启动后，会阻塞线程，也就是为什么要用线程池启动的原因
 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
            stop();
            logger.info("Netty Server close");
        }
    }
    
	private class ServerInitialzer extends ChannelInitializer<SocketChannel>{
			
			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				// TODO Auto-generated method stub
				ChannelPipeline p = ch.pipeline();
				ByteBuf delimiter = Unpooled.copiedBuffer("$$".getBytes());
				p.addLast(new DelimiterBasedFrameDecoder(1024 * 5 , delimiter));
				p.addLast(new StringDecoder());
				p.addLast(new IdleStateHandler(18000, 0, 0, TimeUnit.SECONDS));
				p.addLast(new HeartBeatServerHandler());
				p.addLast(new DeviceValidationHandler());
				p.addLast(new ServiceInboundHandler());
			}
			
		}
 
    @PreDestroy
    public void stop() {
        if (channel != null) {
            logger.info("Netty Server close");
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
 
    @Async//注意这里，组件启动时会执行run，这个注解是让线程异步执行，这样不影响主线程
    @Override
    public void run(String... args) {
        start();
    }

	
}
