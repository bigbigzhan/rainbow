package com.github.rainbow.config;

import com.alibaba.fastjson.JSON;
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
 * @author Gz.
 * @description:
 * @date 2020-06-28 22:58:28
 */
public class RainbowConfigHelper {
    private static final Logger logger = LoggerFactory.getLogger(RainbowConfigHelper.class);
    /** 提供解析,修改bean定义,并与初始化单例 */
    private ConfigurableListableBeanFactory configurableListableBeanFactory;

    /** Rainbow server serverAddress */
    private String serverAddress;


    private static class RainbowConfigHelperInstance{
        //TODO  String localCacheFilePath = System.getProperty("instanceId");
        private static final RainbowConfigHelper instance = new RainbowConfigHelper();
        private static String serverAddress =RainbowConfig.getProperties().getProperty(RainbowConstants.RAINBOW_ADDRESS);


    }
    public static RainbowConfigHelper getInstance(){
        RainbowConfigHelper rainbowConfigHelper = RainbowConfigHelperInstance.instance;
        rainbowConfigHelper.serverAddress = RainbowConfigHelperInstance.serverAddress;
        return rainbowConfigHelper;
    }

    public void savePropertiesToLocalCache(Properties properties) {
        StringBuilder sb = new StringBuilder();
        for (Object o : properties.keySet()) {
            sb.append(o).append("=").append(properties.get(String.valueOf(o))).append("\n");
        }
        if (sb.indexOf("\n") > -1) {
            sb.deleteCharAt(sb.lastIndexOf("\n"));
        }
        String path = properties.getProperty(RainbowConstants.RAINBOW_CONFIG_LOCAL_PATH);

        String env = properties.getProperty(RainbowConstants.RAINBOW_ENV);

        String app = properties.getProperty(RainbowConstants.RAINBOW_APPNAME);

        String groupName = properties.getProperty(RainbowConstants.RAINBOW_GROUPSNAME);

        groupName = groupName.replaceAll(",","-");

        String cachePath  = path + File.separator + RainbowConstants.RAINBOW_CONFIG_PROPERTIES_CACHE  + File.separator + app + File.separator + env;

        try {
            File saveDir = new File(cachePath);
            if(!saveDir.exists()){
                saveDir.mkdirs();
            }
            File file = new File(saveDir,groupName + ".cache");
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(sb.toString().getBytes());
            fos.flush();
            fos.close();
        } catch (IOException e) {
            logger.error("rainbow config properties save cache error",e);
        }
        logger.info("rainbow config properties save success {} ",cachePath);
    }

    public void reloadSpringContextProperties() {
        ConfigurableBeanFactory beanFactory = getConfigurableListableBeanFactory();
        if (beanFactory == null) {
            //spring容器还没初始化完,此次接收到的配置不予处理
            return;
        }
        //开始刷新spring上线文中的SpringValue的值
        for (Object o : RainbowConfig.getProperties().keySet()) {
            SpringValueRegistry springValueRegistry = SpringInjector.getInstance(SpringValueRegistry.class);
            PlaceholderHelper placeholderHelper = SpringInjector.getInstance(PlaceholderHelper.class);

            TypeConverter typeConverter = beanFactory.getTypeConverter();
            Collection<SpringValue> targetValues = springValueRegistry.get(String.valueOf(o));
            for (SpringValue val : targetValues) {
                logger.info("val={}", val);
                Object value = placeholderHelper.resolvePropertyValue(beanFactory, val.getBeanName(), val.getPlaceholder());

                if(val.isJson()){
                    value = JSON.parseObject((String) value,val.getGenericType());
                }else {
                    if(val.isField()){
                        if(testTypeConverterHasConvertIfNecessaryWithFieldParameter()){
                            value = typeConverter.convertIfNecessary(value,val.getTargetType(),val.getField());
                        }else {
                            value = typeConverter.convertIfNecessary(value,val.getTargetType());
                        }
                    }else {
                        value = typeConverter.convertIfNecessary(value,val.getTargetType(),val.getMethodParameter());
                    }
                }
                try {
                    val.update(value);
                } catch (IllegalAccessException e) {
                    logger.error("rainbow update value fial ",e);
                } catch (InvocationTargetException e) {
                    logger.error("rainbow update value fial ",e);
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
    public void setConfigurableListableBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) {
        this.configurableListableBeanFactory = configurableListableBeanFactory;
    }

    public ConfigurableListableBeanFactory getConfigurableListableBeanFactory() {
        return this.configurableListableBeanFactory;
    }

    public String getServerAddress() {
        return serverAddress;
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
