package com.github.rainbow.config;

import com.alibaba.fastjson.JSONObject;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * @author Gz.
 * @description:
 * @date 2020-06-28 22:53:56
 */
public class RainbowConfig {

    //配置信息
    private volatile static Properties properties = new Properties();

    private volatile static Set<String> need_set_null_prop = new HashSet<>();

    public static Set<String> getNeedSetNullProp() {
        Set<String> set = need_set_null_prop;
        return set;
    }


        public static Properties getProperties(){
        Properties prop = properties;
        return prop;
    }

    public static void setProperties(String key,String value){
       properties.setProperty(key, value);
    }

    /**
     * 此处没有直接调用 而是通过反射调用此方法
     * 加载配置文件到Properties中
     */
    private static void reloadProperties(Properties props) {
        properties.clear();
        for (Object s : props.keySet()) {
            properties.put(s, props.get(s));
        }
    }
    private static void reloadProperties(JSONObject jsonObject) {
        jsonObject.keySet().forEach(key->{
            need_set_null_prop.add(key);
            properties.put(key,jsonObject.get(key));
        });
    }
}
