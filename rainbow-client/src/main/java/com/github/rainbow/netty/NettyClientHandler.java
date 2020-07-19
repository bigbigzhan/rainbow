package com.github.rainbow.netty;

import com.alibaba.fastjson.JSONObject;
import com.github.rainbow.common.HeartbeatHandler;
import com.github.rainbow.config.RainbowConfig;
import com.github.rainbow.config.RainbowConfigHelper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * netty客户端处理类
 */
public class NettyClientHandler extends HeartbeatHandler {

    private static final Logger logger = LoggerFactory.getLogger(NettyClientHandler.class);

    private NettyClient client;
    private ChannelHandlerContext channelHandlerContext;
    private ByteBuf byteBuf;

    public NettyClientHandler(NettyClient client) {
        super("client");
        this.client = client;
    }

    @Override
    protected void handleData(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) {
        byte[] data = new byte[byteBuf.readableBytes() - 5];
        byteBuf.skipBytes(5);
        byteBuf.readBytes(data);
        String content = new String(data);
        logger.debug("接收到参数信息:{}",content);

        try {
            //为保证ConfigCenter只对用户暴露获取相关的方法，这里通过反射调用load方法刷新配置
            Method reloadProperties = RainbowConfig.class.getDeclaredMethod("reloadProperties", JSONObject.class);
            //类中的方法为private,故必须进行此操作
            reloadProperties.setAccessible(true);
            JSONObject prop = JSONObject.parseObject(content);
            //调用
            reloadProperties.invoke(JSONObject.class,prop);
        } catch (Exception e) {
            logger.warn("刷新配置出现异常,msg:{}"+e.getMessage());
        }

        //将配置文件重新写入本地缓存文件中
        RainbowConfigHelper.getInstance().savePropertiesToLocalCache(RainbowConfig.getProperties());

        //重新加载spring上下中的配置
        RainbowConfigHelper.getInstance().reloadSpringContextProperties();

    }

    @Override
    protected void handleAllIdle(ChannelHandlerContext ctx) {
        super.handleAllIdle(ctx);
        sendPingMsg(ctx);
    }


    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        NettyClient.waitQueue.add(5000L);
        client.doConnect();
    }


}
