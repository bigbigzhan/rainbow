package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 应用信息对象 rainbow_application
 * 
 * @author Gz
 * @date 2020-07-04
 */
public class RainbowApplication extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 应用名称 */
    private String appName;

    /** 应用说明 */
    @Excel(name = "应用说明")
    private String description;

    public void setAppName(String appName) 
    {
        this.appName = appName;
    }

    public String getAppName() 
    {
        return appName;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("appName", getAppName())
            .append("description", getDescription())
            .toString();
    }
}
