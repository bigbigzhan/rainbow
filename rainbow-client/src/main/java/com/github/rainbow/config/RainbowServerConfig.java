package com.github.rainbow.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Gz.
 * @description: 服务器配置信息类
 * @date 2020-07-07 22:21:39
 */
public class RainbowServerConfig {



    private String address;
    private String env;
    private String appName;
    private String groupsName;

    public RainbowServerConfig() {
        System.out.println("1" +address + "#" + "#" + env + "#" + appName + "#" + groupsName);
    }
    public RainbowServerConfig(String address, String env, String appName, String groupsName) {
        this.address = address;
        this.env = env;
        this.appName = appName;
        this.groupsName = groupsName;

        System.out.println( "2" +address + "#" + "#" + env + "#" + appName + "#" + groupsName);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getGroupsName() {
        return groupsName;
    }

    public void setGroupsName(String groupsName) {
        this.groupsName = groupsName;
    }
}
