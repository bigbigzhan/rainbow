package com.github.rainbow.Controller;

import com.alibaba.fastjson.JSONObject;
import com.github.rainbow.config.RainbowConfig;
import com.github.rainbow.config.RainbowConfigHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TestUtil {

    @Autowired
    ConfigurableApplicationContext applicationContext;
    private ConfigurableListableBeanFactory configurableListableBeanFactory;
    private static final Logger logger = LoggerFactory.getLogger(TestUtil.class);

    private ConcurrentHashMap propertiesMap = new ConcurrentHashMap();

    public  void  updateConfigurableEnvSource(){
//        MutablePropertySources propertySources = applicationContext.getEnvironment().getPropertySources();
//        for (PropertySource<?> propertySource : propertySources) {
//            if(RainbowConstants.RAINBOW_CONFIG_PROPERTIES_NAME.equalsIgnoreCase(propertySource.getName())){
//                System.out.println(propertySource.getName());
//                System.out.println(propertySource.getSource());
//                pullRemoteServerProperties(propertiesMap);
//                OriginTrackedMapPropertySource originTrackedMapPropertySource = new OriginTrackedMapPropertySource(RainbowConstants.RAINBOW_CONFIG_PROPERTIES_NAME,propertiesMap);
//                propertySources.addFirst(originTrackedMapPropertySource);
//            }
//        }

        try {
            //TODO 假装收到服务通知新参数变更
            String content = pullRemoteServerProperties(null, 0);
            Method reloadProperties = RainbowConfig.class.getDeclaredMethod("reloadProperties", JSONObject.class);
            reloadProperties.setAccessible(true);
            JSONObject jsonObject = JSONObject.parseObject(content);
            reloadProperties.invoke(JSONObject.class,jsonObject);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e ) {
            logger.error("Rainbow updateConfigurableEnvSource error",e);
        }
        RainbowConfigHelper.getInstance().savePropertiesToLocalCache(RainbowConfig.getProperties());

        RainbowConfigHelper.getInstance().reloadSpringContextProperties();
    }

    private String pullRemoteServerProperties(String host, int ip) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("config.username","aaa");
        jsonObject.put("config.password","pwd");
        jsonObject.put("config.mobile","1762127");
        return jsonObject.toString();
    }
    private void pullRemoteServerProperties(ConcurrentHashMap source) {
        source.put("config.username","ggggzzz");
    }
}
