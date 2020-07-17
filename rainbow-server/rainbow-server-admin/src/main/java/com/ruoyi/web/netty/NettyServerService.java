package com.ruoyi.web.netty;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.system.domain.RainbowCells;
import com.ruoyi.system.service.IRainbowCellsService;
import com.ruoyi.system.service.IRainbowGroupsService;
import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Netty相关服务接口实现
 * @author xlizy
 * @date 2018/5/28
 */
@Service
public class NettyServerService  {

    private static final Logger log = LoggerFactory.getLogger(NettyServerService.class);

    @Autowired
    IRainbowGroupsService groupsService;
    @Autowired
    IRainbowCellsService cellsService;

    public void sendConfig(String env, String app,List<String> groupName, SendConfigType type,String key) {
                    List<RainbowCells> cellList = cellsService.queryCellsByGroups(env, app, groupName);
                    JSONObject prop = new JSONObject();
                    cellList.forEach(dto->prop.put(dto.getRainbowKey(),dto.getRainbowValue()));
                    log.info("将对应的配置响应给客户端,response:{}",prop);
                    //将配置推送记录到log表里
                    //  ThreadPools.publicUsePool.submit(() -> SpringContextUtil.getBean(PushPropertiesLogService.class).addPushPropertiesLog(_app,_env,_version,_cluster,c.remoteAddress().toString(),type) );
                    NettyServer.allServerSocketChannel.get(key).forEach(c -> {
                        //往通道写数据
                        log.info("发送给:{}", c);
                        ByteBuf buf = c.alloc().buffer(5 + prop.toJSONString().getBytes().length);
                        buf.writeInt(5 + prop.toJSONString().getBytes().length);
                        buf.writeByte(HeartbeatHandler.DATA_MSG);
                        buf.writeBytes(prop.toJSONString().getBytes());
                        c.writeAndFlush(buf);
                    });
    }

    public void sendConfig(Long groupId, SendConfigType type,String key) {
        List<RainbowCells> cellList = cellsService.queryCellsByGroupId(groupId);
        JSONObject prop = new JSONObject();
        cellList.forEach(dto->prop.put(dto.getRainbowKey(),dto.getRainbowValue()));
        log.info("将对应的配置响应给客户端,response:{}",prop);
        //将配置推送记录到log表里
        NettyServer.allServerSocketChannel.get(key).forEach(c -> {
            //往通道写数据
            log.info("发送给:{}", c);
            ByteBuf buf = c.alloc().buffer(5 + prop.toJSONString().getBytes().length);
            buf.writeInt(5 + prop.toJSONString().getBytes().length);
            buf.writeByte(HeartbeatHandler.DATA_MSG);
            buf.writeBytes(prop.toJSONString().getBytes());
            c.writeAndFlush(buf);
        });
    }

}
