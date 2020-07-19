package com.rainbow.web.netty;



/**
 * 配置推送类型
 */
public enum SendConfigType  {

    /** 配置推送类型 */
    SCHEDULED(0,"定时推送配置"),
    EDIT(1,"配置有修改时推送"),
    INITIATIVE(2,"客户端主动获取配置");

    private int val;
    private String des;

    SendConfigType() {
    }

    SendConfigType(int val, String des) {
        this.val = val;
        this.des = des;
    }

    public int getVal() {
        return val;
    }

    public String getDes() {
        return des;
    }
}
