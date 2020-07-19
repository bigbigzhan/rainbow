package com.github.rainbow.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 心跳处理
 */
public abstract class HeartbeatHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private static final Logger logger = LoggerFactory.getLogger(HeartbeatHandler.class);

    /** 定义 ping 类型 */
    public static final byte PING_MSG = 1;
    /** 定义 pong 类型 */
    public static final byte PONG_MSG = 2;
    /** 定义 普通数据 类型 */
    public static final byte DATA_MSG = 3;

    protected String name;

    /** 心跳次数 */
    private int heartbeatCount = 0;

    public HeartbeatHandler(String name) {
        this.name = name;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext context, ByteBuf byteBuf) throws Exception {
        if (byteBuf.getByte(4) == PING_MSG) {
            sendPongMsg(context);
        } else if (byteBuf.getByte(4) == PONG_MSG){
            logger.info("{} get pong msg from {}",name,context.channel().remoteAddress());
        } else {
            handleData(context, byteBuf);
        }
    }

    protected void sendPingMsg(ChannelHandlerContext context) {
        ByteBuf buf = context.alloc().buffer(5);
        buf.writeInt(5);
        buf.writeByte(PING_MSG);
        context.writeAndFlush(buf);
        heartbeatCount++;
        logger.debug("{} sent ping msg to {}, count: {}",name,context.channel().remoteAddress(),heartbeatCount);
    }

    private void sendPongMsg(ChannelHandlerContext context) {
        ByteBuf buf = context.alloc().buffer(5);
        buf.writeInt(5);
        buf.writeByte(PONG_MSG);
        context.channel().writeAndFlush(buf);
        heartbeatCount++;
        logger.debug("{} sent pong msg to {}, count: {}",name,context.channel().remoteAddress(),heartbeatCount);
    }

    /**
     * 处理接收到的非心跳数据
     * @param channelHandlerContext
     * @param byteBuf
     * */
    protected abstract void handleData(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf);

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        // IdleStateHandler 所产生的 IdleStateEvent 的处理逻辑.
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            switch (e.state()) {
                case READER_IDLE:
                    handleReaderIdle(ctx);
                    break;
                case WRITER_IDLE:
                    handleWriterIdle(ctx);
                    break;
                case ALL_IDLE:
                    handleAllIdle(ctx);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.debug("{} is active",ctx.channel().remoteAddress());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.debug("{} is inactive",ctx.channel().remoteAddress());
    }

    protected void handleReaderIdle(ChannelHandlerContext ctx) {
        logger.debug("{} is READER_IDLE",ctx.channel().remoteAddress());
    }

    protected void handleWriterIdle(ChannelHandlerContext ctx) {
        logger.debug("{} is WRITER_IDLE",ctx.channel().remoteAddress());
    }

    protected void handleAllIdle(ChannelHandlerContext ctx) {
        logger.debug("{} is ALL_IDLE",ctx.channel().remoteAddress());
    }
}
