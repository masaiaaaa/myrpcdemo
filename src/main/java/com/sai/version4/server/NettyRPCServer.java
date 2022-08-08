package com.sai.version4.server;

/**
 * @Description: TODO
 * @author: sai
 * @date: 2022年08月08日 14:17
 */

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.AllArgsConstructor;

/**
 * 实现RPCServer接口，负责监听与发送数据
 */
@AllArgsConstructor
public class NettyRPCServer implements RPCServer{

    private ServiceProvider serviceProvider;

    @Override
    public void start(int port) {

        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        System.out.println("netty服务端启动了");

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new NettyServerInitializer(serviceProvider));

            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            channelFuture.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

    @Override
    public void stop() {

    }
}
