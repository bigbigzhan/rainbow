package com.github.rainbow.initialization;

import com.alibaba.fastjson.JSONObject;
import com.github.rainbow.common.ClientHelper;
import com.github.rainbow.common.HeartbeatHandler;
import com.github.rainbow.config.RainbowConfig;
import com.github.rainbow.config.RainbowConfigHelper;
import com.github.rainbow.config.RainbowConstants;
import com.github.rainbow.netty.NettyClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.PropertiesPropertySource;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Order(value = Integer.MIN_VALUE)
public class ClientConfigPropertisInitialization  implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final Logger logger = LoggerFactory.getLogger(ClientConfigPropertisInitialization.class);

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        Properties properties = initializeConfigCenterProperties();
        if(properties.isEmpty()){
            //TODO 获取远程连接从远程拉去配置 如果拉去失败则从本地拉取
        }
        //将配置装配到spring容器中
        setPropertiesToSpringEnviroment(configurableApplicationContext, properties);

        NettyClient nettyClient = NettyClient.getInstance();
        nettyClient.start();
    }

    private void setPropertiesToSpringEnviroment(ConfigurableApplicationContext configurableApplicationContext, Properties properties) {
        PropertiesPropertySource propertySource = new PropertiesPropertySource(RainbowConstants.RAINBOW_CONFIG_PROPERTIES_NAME, properties);
        configurableApplicationContext.getEnvironment().getPropertySources().addFirst(propertySource);
    }

    //init server remote rainbow server pull properties
    public Properties initializeConfigCenterProperties(){
        //配置中心host
        final String serverAddress = RainbowConfigHelper.getInstance().getServerAddress();
        logger.info("serverAddress{}",serverAddress);

        try {
              // 拉取服务配置文件
             String propertiesStr = pullRemoteServerProperties();
            //反射调用
            Method reloadProperties = RainbowConfig.class.getDeclaredMethod("reloadProperties", JSONObject.class);
            //private 方法
            reloadProperties.setAccessible(true);
            JSONObject parse = JSONObject.parseObject(propertiesStr);
            reloadProperties.invoke(JSONObject.class,parse);

            //将配置文件重新写入本地缓存文件中
            RainbowConfigHelper.getInstance().savePropertiesToLocalCache(RainbowConfig.getProperties());

            } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException exception ) {
                logger.error("connection Rainbow Server error",exception);
                return new Properties();
            }

    return RainbowConfig.getProperties();
    }

    private String pullRemoteServerProperties() {
        String listen = null;
        try {
            String[] split = RainbowConfigHelper.getInstance().getServerAddress().split(":");
            String host = split[0];
            int port = Integer.parseInt(split[1]);
            logger.info("host={}",host);
            logger.info("port={}",port);
            listen = new Client().init(host,port).listen();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listen;
    }


    private void pullRemoteServerProperties(ConcurrentHashMap source) {
        source.put("config.username","高瞻");
    }
    class Client{
        /** 管道管理器 */
        private Selector selector;

        public Client init(String serverIp, int port) throws IOException {
            //获取socket通道
            SocketChannel channel = SocketChannel.open();

            channel.configureBlocking(false);
            //获得通道管理器
            selector=Selector.open();

            //客户端连接服务器，需要调用channel.finishConnect();才能实际完成连接。
            channel.connect(new InetSocketAddress(serverIp, port));
            //为该通道注册SelectionKey.OP_CONNECT事件
            channel.register(selector, SelectionKey.OP_CONNECT);
            return this;
        }

        public String listen() throws IOException {
            JSONObject request = new JSONObject();
            Properties properties = RainbowConfig.getProperties();
            request.put(RainbowConstants.RAINBOW_SERVER_APP,properties.getProperty(RainbowConstants.RAINBOW_APPNAME));
            request.put(RainbowConstants.RAINBOW_SERVER_ENV,properties.getProperty(RainbowConstants.RAINBOW_ENV));
            request.put(RainbowConstants.RAINBOW_SERVER_ROUPSNAME,properties.getProperty(RainbowConstants.RAINBOW_GROUPSNAME));

            while(true){
                //选择注册过的io操作的事件(第一次为SelectionKey.OP_CONNECT)
                selector.select();
                Iterator<SelectionKey> ite = selector.selectedKeys().iterator();
                while(ite.hasNext()){
                    SelectionKey key = ite.next();
                    //删除已选的key，防止重复处理
                    ite.remove();
                    if(key.isConnectable()){
                        SocketChannel channel=(SocketChannel)key.channel();

                        //如果正在连接，则完成连接
                        if(channel.isConnectionPending()){
                            channel.finishConnect();
                        }

                        channel.configureBlocking(false);
                        //向服务器发送消息
                        byte[] body = request.toJSONString().getBytes();
                        int bodyLength = body.length;
                        int totalLength = 5 + bodyLength;
                        byte[] data = new byte[totalLength];

                        data[0] = (byte) ((totalLength >> 24) & 0xFF);
                        data[1] = (byte) ((totalLength >> 16) & 0xFF);
                        data[2] = (byte) ((totalLength >> 8) & 0xFF);
                        data[3] = (byte) (totalLength & 0xFF);
                        data[4] = HeartbeatHandler.DATA_MSG;
                        for (int i = 5; i < data.length; i++) {
                            data[i] = body[i-5];
                        }
                        channel.write(ByteBuffer.wrap(data));

                        //连接成功后，注册接收服务器消息的事件
                        channel.register(selector, SelectionKey.OP_READ);
                        logger.debug("首次连接config-center-server成功");
                        //有可读数据事件。
                    }else if(key.isReadable()){
                        SocketChannel channel = (SocketChannel)key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(0x200000);
                        channel.read(buffer);
                        byte[] data = buffer.array();
                        String msg = new String(data,5,data.length - 5);
                        logger.debug("首次连接config-center-server,并接受到返回值:{}",msg);

                        channel.close();
                        return msg;
                    }
                }
            }
        }
    }
}
