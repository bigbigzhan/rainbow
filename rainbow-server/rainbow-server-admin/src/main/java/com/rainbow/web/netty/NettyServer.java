package com.rainbow.web.netty;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyServer {

    private static final Logger logger = LoggerFactory.getLogger(NettyServer.class);

    public static Multimap<String, Channel> allServerSocketChannel = LinkedListMultimap.create();

    public NettyServer(int serverPort){
        bind(serverPort);
    }

    private void bind(int serverPort) {
        ThreadPools.publicUsePool.execute(() -> {
            //服务端要建立两个group，一个负责接收客户端的连接，一个负责处理数据传输
            //连接处理group
            EventLoopGroup bossGroup = new NioEventLoopGroup(1);
            //事件处理group
            EventLoopGroup workGroup = new NioEventLoopGroup(4);
            try {
                ServerBootstrap bootstrap = new ServerBootstrap();
                bootstrap
                        .group(bossGroup, workGroup)
                        .channel(NioServerSocketChannel.class)
                        .childHandler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel socketChannel) throws Exception {
                                socketChannel.pipeline().addLast(
                                        new IdleStateHandler(0, 0, 25),
                                        new LengthFieldBasedFrameDecoder(0x200000, 0, 4, -4, 0),
                                        new NettyServerHandler());
                            }
                        });

                Channel ch = bootstrap.bind(serverPort).sync().channel();
                ch.closeFuture().sync();
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                bossGroup.shutdownGracefully();
                workGroup.shutdownGracefully();
            }
        });
    }


}
