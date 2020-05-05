package com.env.io.netty;

import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.env.io.netty.handler.DeviceValidationHandler;
import com.env.io.netty.handler.HeartBeatServerHandler;
import com.env.io.netty.handler.ServiceInboundHandler;
import io.netty.channel.EventLoopGroup;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * netty服务配置初始化
 * @author lizheng
 * @date 2020年4月9日
 *
 */
@Component
public class NettyServer {
	
	@Autowired
	private ServerInitialzer serverInitialzer;
	
	public static class SingletionNetServer{
		static final NettyServer instance = new NettyServer(); 
	}
	
	public static NettyServer getInstance(){
		return SingletionNetServer.instance;
	}
	
	//服务端nio线程组，一个用于接受客户端连接，一个用于进行socketchanne的网络读写
	private EventLoopGroup mainGroup;
	private EventLoopGroup subGroup;
	//Netty用于启动nio服务端的辅助启动类，目的是降低开发复杂度
	private ServerBootstrap server;
	private ChannelFuture future;
	
	public void start(int port) throws InterruptedException{
		mainGroup = new NioEventLoopGroup();
		subGroup = new NioEventLoopGroup();
		try {
			server = new ServerBootstrap();
			server.group(mainGroup, subGroup)
					.channel(NioServerSocketChannel.class)
					//配置NioServerSocketChannel的tcp参数https://www.jianshu.com/p/a81ec7a47959
					.option(ChannelOption.SO_BACKLOG, 1024)
					.childHandler(serverInitialzer);	//添加自定义初始化处理器
			//绑定端口，同步等待成功
			future = this.server.bind(port).sync();
			System.out.println("netty 服务端启动完毕 .....");
			//等待服务端监听端口关闭
			future.channel().closeFuture().sync();
		}finally {
			//优雅退出，释放线程池资源
			mainGroup.shutdownGracefully();
			subGroup.shutdownGracefully();
		}
		
	}
	
	@Component
	private class ServerInitialzer extends ChannelInitializer<SocketChannel>{
		
		@Autowired
		private HeartBeatServerHandler heartBeatServerHandler;
		@Autowired
		private DeviceValidationHandler deviceValidationHandler;
		
		@Override
		protected void initChannel(SocketChannel ch) throws Exception {
			// TODO Auto-generated method stub
			ChannelPipeline p = ch.pipeline();
			ByteBuf delimiter = Unpooled.copiedBuffer("$$".getBytes());
			p.addLast(new DelimiterBasedFrameDecoder(1024 * 5 , delimiter));
			p.addLast(new StringDecoder());
			p.addLast(new IdleStateHandler(180, 0, 0, TimeUnit.SECONDS));
			p.addLast(heartBeatServerHandler);
			p.addLast(deviceValidationHandler);
			p.addLast(new ServiceInboundHandler());
		}
		
	}

}
