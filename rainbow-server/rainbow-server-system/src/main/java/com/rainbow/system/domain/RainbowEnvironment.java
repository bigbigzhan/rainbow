package com.rainbow.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.rainbow.common.annotation.Excel;
import com.rainbow.common.core.domain.BaseEntity;

/**
 * 环境信息对象 rainbow_environment
 *
 * @author Gz
 * @date 2020-07-04
 */
public class RainbowEnvironment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 环境名称 */
    private String env;

    /** 环境显示说明 */
    @Excel(name = "环境显示说明")
    private String description;

    public void setEnv(String env)
    {
        this.env = env;
    }

    public String getEnv()
    {
        return env;
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
            .append("env", getEnv())
            .append("description", getDescription())
            .toString();
    }
}
