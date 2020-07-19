package com.rainbow.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.rainbow.common.annotation.Excel;
import com.rainbow.common.core.domain.BaseEntity;

/**
 * rainbow配置组对象 rainbow_groups
 * 
 * @author Gz
 * @date 2020-07-04
 */
public class RainbowGroups extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 环境名称 */
    @Excel(name = "环境名称")
    private String env;

    /** 应用名称 */
    @Excel(name = "应用名称")
    private String appName;

    /** 组名称 */
    @Excel(name = "组名称")
    private String groupName;

    /** 组描述 */
    @Excel(name = "组描述")
    private String groupDesc;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createUser;

    /** 修改人 */
    @Excel(name = "修改人")
    private String updateUser;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setEnv(String env) 
    {
        this.env = env;
    }

    public String getEnv() 
    {
        return env;
    }
    public void setAppName(String appName) 
    {
        this.appName = appName;
    }

    public String getAppName() 
    {
        return appName;
    }
    public void setGroupName(String groupName) 
    {
        this.groupName = groupName;
    }

    public String getGroupName() 
    {
        return groupName;
    }
    public void setGroupDesc(String groupDesc) 
    {
        this.groupDesc = groupDesc;
    }

    public String getGroupDesc() 
    {
        return groupDesc;
    }
    public void setCreateUser(String createUser) 
    {
        this.createUser = createUser;
    }

    public String getCreateUser() 
    {
        return createUser;
    }
    public void setUpdateUser(String updateUser) 
    {
        this.updateUser = updateUser;
    }

    public String getUpdateUser() 
    {
        return updateUser;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("env", getEnv())
            .append("appName", getAppName())
            .append("groupName", getGroupName())
            .append("groupDesc", getGroupDesc())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createUser", getCreateUser())
            .append("updateUser", getUpdateUser())
            .toString();
    }
}
