package com.ruoyi.web.netty;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.util.Assert;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class NettyServerHandler extends HeartbeatHandler {
    private static final Logger log = LoggerFactory.getLogger(NettyServerHandler.class);

    public NettyServerHandler() {
        super("server");
    }

    @Override
    protected void handleData(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) {

        MDC.put(CoreUtil.TRACE_ID, UUID.randomUUID().toString());

        byte[] data = new byte[byteBuf.readableBytes() - 5];
        byteBuf.skipBytes(5);
        byteBuf.readBytes(data);
        String content = new String(data);
        log.info("接收到客户端请求信息:{}",content);

        JSONObject object = JSON.parseObject(content);
        String groupsStr = object.getString("groupsName");
        String env = object.getString("env");
        String app = object.getString("app");
        Assert.notNull(app,"app must not null");
        Assert.notNull(env,"env must not null");
        Assert.notNull(groupsStr,"groups must not null");

        String[] split = groupsStr.split(",");
        List<String> gruopList = Lists.newArrayList();
        for (String group : split) {
            StringBuilder sb = new StringBuilder()
                    .append(env)
                    .append("#")
                    .append(app)
                    .append("#")
                    .append(group);
            gruopList.add(group);
            String key = sb.toString();
            log.info("客户端来自:{}",key);
            NettyServer.allServerSocketChannel.put(key,channelHandlerContext.channel());
        }
        StringBuilder sb = new StringBuilder()
                .append(env)
                .append("#")
                .append(app)
                .append("#")
                .append(groupsStr);
        String key = sb.toString();

        NettyServer.allServerSocketChannel.put(sb.toString(),channelHandlerContext.channel());
        SpringContextUtil.getBean(NettyServerService.class).sendConfig(env,app,gruopList,SendConfigType.INITIATIVE,key);

    }

    @Override
    protected void handleReaderIdle(ChannelHandlerContext ctx) {
        super.handleReaderIdle(ctx);
        log.warn("{} reader timeout, close it",ctx.channel().remoteAddress().toString());
        clearChannel(ctx);
        ctx.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        clearChannel(ctx);
        ctx.close();
    }

    /**
     * 清除失效的channel
     * */
    private void clearChannel(ChannelHandlerContext ctx){
        log.debug("clear invalid channel");
        Set<String> set = NettyServer.allServerSocketChannel.keySet();
        Iterator setItr = set.iterator();
        while(setItr.hasNext()){
            Object k = setItr.next();
            boolean b = NettyServer.allServerSocketChannel.get(String.valueOf(k)).remove(ctx.channel());
            if(b){
                break;
            }
        }
    }
}
