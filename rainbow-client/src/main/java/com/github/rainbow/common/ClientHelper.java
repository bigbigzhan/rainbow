package com.github.rainbow.common;

import com.alibaba.fastjson.JSON;
import com.github.rainbow.config.RainbowConfig;
import com.github.rainbow.config.RainbowConstants;
import com.github.rainbow.property.SpringValue;
import com.github.rainbow.property.SpringValueRegistry;
import com.github.rainbow.util.PlaceholderHelper;
import com.github.rainbow.util.SpringInjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeConverter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Properties;

/**
 * 客户端帮助类
 * @author xlizy
 * @date 2018/5/10
 */
public class ClientHelper {

    private static final Logger logger = LoggerFactory.getLogger(ClientHelper.class);

    /** 提供解析,修改bean定义,并与初始化单例 */
    private ConfigurableListableBeanFactory configurableListableBeanFactory;

    /** 记录config-center-server的host */
    private String host;
    /** 记录config-center-server的port */
    private int port;

    //region 单例模式,静态内部类实现

    private ClientHelper(){
        //为防止通过反射进行实例化操作打破单例
        if(ClientHelpertInstance.instance != null){
            throw new RuntimeException("不可以通过反射进行实例化呦~");
        }
    }

    private static class ClientHelpertInstance{
        private static final ClientHelper instance = new ClientHelper();

        private static String host = "127.0.0.1";
        private static int port = 7777;
        static {
            String[] split = System.getProperty(RainbowConstants.RAINBOW_ADDRESS).split(":");

            //region 试图从配置文件中获取host和端口
            //Properties prop = new Properties();
            //String localCacheFilePath = System.getProperty("user.home");
            //
            //if(isOSWindows()){
            //    localCacheFilePath += "\\.configcenter\\config-config.cfg";
            //}else{
            //    localCacheFilePath += "/.configcenter/config-config.cfg";
            //}
            //File file = new File(localCacheFilePath);
            //if(file.exists()){
            //    FileInputStream fis = null;
            //    InputStreamReader isr = null;
            //    try {
            //        fis = new FileInputStream(file);
            //        isr = new InputStreamReader(fis,"UTF-8");
            //        prop.load(isr);
            //        host = (String) prop.get("host");
            //        port = Integer.parseInt((String)prop.get("port"));
            //    } catch (FileNotFoundException e) {
            //        e.printStackTrace();
            //    } catch (UnsupportedEncodingException e) {
            //        e.printStackTrace();
            //    } catch (IOException e) {
            //        e.printStackTrace();
            //    }finally {
            //        if(isr != null){
            //            try {
            //                isr.close();
            //            } catch (IOException e) {
            //                e.printStackTrace();
            //            }
            //        }
            //        if(fis != null){
            //            try {
            //                fis.close();
            //            } catch (IOException e) {
            //                e.printStackTrace();
            //            }
            //        }
            //    }
            //}
            host = split[0];
            port = Integer.parseInt(split[1]);
        }
    }

    public static ClientHelper getInstance(){
        ClientHelper clientHelper = ClientHelpertInstance.instance;
        clientHelper.host = ClientHelpertInstance.host;
        clientHelper.port = ClientHelpertInstance.port;
        return clientHelper;
    }

    //endregion

    public ConfigurableListableBeanFactory getConfigurableListableBeanFactory() {
        return this.configurableListableBeanFactory;
    }

    public void setConfigurableListableBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) {
        this.configurableListableBeanFactory = configurableListableBeanFactory;
    }


    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    /**
     * 将配置写入本地缓存
     * */
    public void savePropertiesToLocalCache(Properties p){
        StringBuilder sb = new StringBuilder();
        for (Object o : p.keySet()) {
            sb.append(o).append("=").append(p.get(String.valueOf(o))).append("\n");
        }
        if(sb.indexOf("\n") > -1){
            sb.deleteCharAt(sb.lastIndexOf("\n"));
        }

        //本地缓存文件的文件全路径
        //用户主目录
        String localCacheFilePath = System.getProperty("user.home");
        //应用名称
        String app = System.getProperty("app.name");
        //环境
        String env = System.getProperty("app.env","dev");
        //版本
        String version = System.getProperty("app.version","1.0.0");
        //集群/机房
        String cluster = System.getProperty("app.cluster","default");
        if(isOSWindows()){
            localCacheFilePath += "\\.configcenter\\" + host + "_" + port + "_" + app + "_" + env + "_" + version + "_" + cluster + ".cache";
        }else{
            localCacheFilePath += "/.configcenter/" + host + "_" + port + "_" + app + "_" + env + "_" + version + "_" + cluster + ".cache";
        }
        File file = new File(localCacheFilePath);
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        try {
            fos = new FileOutputStream(file);
            osw = new OutputStreamWriter(fos,"UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(sb.toString());
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(osw != null){
                try {
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 重新加载spring上下文中的配置
     * 这方法的实现 借鉴了携程的Apollo配置中心代码
     * */
    public void reloadSpringContextProperties(){
        ConfigurableBeanFactory beanFactory = getConfigurableListableBeanFactory();
        if(beanFactory == null){
            logger.warn("spring容器还没初始化完,此次接收到的配置不予处理");
            return;
        }
        logger.debug("开始刷新spring上线文中的SpringValue的值(置空未启用的属性)");
        for (String o : RainbowConfig.getNeedSetNullProp()) {
            SpringValueRegistry springValueRegistry = SpringInjector.getInstance(SpringValueRegistry.class);
            Collection<SpringValue> targetValues = springValueRegistry.get(o);
            for (SpringValue val : targetValues) {
                try {
                    val.update(null);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        logger.debug("开始刷新spring上线文中的SpringValue的值");
        for (Object o : RainbowConfig.getProperties().keySet()) {
            SpringValueRegistry springValueRegistry = SpringInjector.getInstance(SpringValueRegistry.class);
            PlaceholderHelper placeholderHelper = SpringInjector.getInstance(PlaceholderHelper.class);

            TypeConverter typeConverter = beanFactory.getTypeConverter();
            Collection<SpringValue> targetValues = springValueRegistry.get(String.valueOf(o));
            for (SpringValue val : targetValues) {
                // value will never be null, as @Value and @ApolloJsonValue will not allow that
                Object value = placeholderHelper
                        .resolvePropertyValue(beanFactory, val.getBeanName(), val.getPlaceholder());

                if (val.isJson()) {
                    value = JSON.parseObject((String)value, val.getGenericType());
                } else {
                    if (val.isField()) {
                        // org.springframework.beans.TypeConverter#convertIfNecessary(java.lang.Object, java.lang.Class, java.lang.reflect.Field) is available from Spring 3.2.0+
                        if (testTypeConverterHasConvertIfNecessaryWithFieldParameter()) {
                            value = typeConverter
                                    .convertIfNecessary(value, val.getTargetType(), val.getField());
                        } else {
                            value = typeConverter.convertIfNecessary(value, val.getTargetType());
                        }
                    } else {
                        value = typeConverter.convertIfNecessary(value, val.getTargetType(),
                                val.getMethodParameter());
                    }
                }

                try {
                    val.update(value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private boolean testTypeConverterHasConvertIfNecessaryWithFieldParameter() {
        try {
            TypeConverter.class.getMethod("convertIfNecessary", Object.class, Class.class, Field.class);
        } catch (Throwable ex) {
            return false;
        }
        return true;
    }

    /** 获取操作系统类型 */
    public static boolean isOSWindows() {
        String osName = System.getProperty("os.name");
        if (osName == null || "".trim().equals(osName) ) {
            return false;
        }
        return osName.startsWith("Windows");
    }

}
