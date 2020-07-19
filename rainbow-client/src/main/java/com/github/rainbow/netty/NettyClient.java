package com.github.rainbow.netty;

import com.alibaba.fastjson.JSONObject;
import com.github.rainbow.common.HeartbeatHandler;
import com.github.rainbow.config.RainbowConfig;
import com.github.rainbow.config.RainbowConfigHelper;
import com.github.rainbow.config.RainbowConstants;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * netty客户端
 */
public class NettyClient {

    private static final Logger logger = LoggerFactory.getLogger(NettyClient.class);

    protected static final Queue<Long> waitQueue = new LinkedList<>();

    private NioEventLoopGroup workGroup = new NioEventLoopGroup(4);
    private Channel channel;
    private Bootstrap bootstrap;

    /** 用来限制start()只执行一次的 */
    private final AtomicBoolean INITIALIZED = new AtomicBoolean(false);

    //region 单例模式,静态内部类实现

    private NettyClient(){
        //为防止通过反射进行实例化操作打破单例
        if(ClientInstance.instance != null){
            throw new RuntimeException("不可以通过反射进行实例化呦~");
        }
    }

    private static class ClientInstance{
        private static final NettyClient instance = new NettyClient();
    }

    public static NettyClient getInstance(){
        NettyClient nettyClient = ClientInstance.instance;
        return nettyClient;
    }

    //endregion 单例

    /**
     * client的核心方法
     * */
    public void start() {
        if (INITIALIZED.compareAndSet(false, true)) {
            bootstrap = new Bootstrap();
            bootstrap
                    .group(workGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(
                                    new IdleStateHandler(0, 0, 25),
                                    new LengthFieldBasedFrameDecoder(0x200000, 0, 4, -4, 0),
                                     new NettyClientHandler(NettyClient.this));
                        }
                    });
            doConnect();
        }
    }


    protected void doConnect() {

        try {
            Long l = waitQueue.poll();
            if(l != null){
                Thread.sleep(l);
            }
        } catch (Exception e) {
            logger.warn("暂停失败,msg:{}",e.getMessage());
        }

        //从~/.configcenter/config-config.cfg文件中获取配置中心的host和port
        String[] split = RainbowConfigHelper.getInstance().getServerAddress().split(":");
        String host = split[0];
        int port = Integer.parseInt(split[1]);
        final JSONObject body = new JSONObject();
        Properties properties = RainbowConfig.getProperties();
        body.put(RainbowConstants.RAINBOW_SERVER_APP,properties.getProperty(RainbowConstants.RAINBOW_APPNAME));
        body.put(RainbowConstants.RAINBOW_SERVER_ENV,properties.getProperty(RainbowConstants.RAINBOW_ENV));
        body.put(RainbowConstants.RAINBOW_SERVER_ROUPSNAME,properties.getProperty(RainbowConstants.RAINBOW_GROUPSNAME));

        if (channel != null && channel.isActive()) {
            return;
        }

        ChannelFuture future = bootstrap.connect(host, port);

        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture futureListener) throws Exception {
                if (futureListener.isSuccess()) {
                    channel = futureListener.channel();

                    ByteBuf buf = channel.alloc().buffer(5 + body.toJSONString().getBytes().length);
                    buf.writeInt(5 + body.toJSONString().getBytes().length);
                    buf.writeByte(HeartbeatHandler.DATA_MSG);
                    buf.writeBytes(body.toJSONString().getBytes());
                    channel.writeAndFlush(buf);

                    logger.debug("Connect to server successfully!");
                } else {
                    logger.debug("Failed to connect to server, try connect after 10s");
                    futureListener.channel().eventLoop().schedule(new Runnable() {
                        @Override
                        public void run() {
                            doConnect();
                        }
                    }, 10, TimeUnit.SECONDS);
                }
            }
        });
    }

}
