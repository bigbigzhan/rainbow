package com.rainbow.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.rainbow.common.annotation.Excel;
import com.rainbow.common.core.domain.BaseEntity;

/**
 * 配置项目对象 rainbow_cells
 * 
 * @author Gz
 * @date 2020-07-04
 */
public class RainbowCells extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增Id */
    private Long id;

    /** 组ID */
    @Excel(name = "组ID")
    private Long groupId;

    private String groupName;

    /** 配置项Key */
    @Excel(name = "配置项Key")
    private String rainbowKey;

    /** 配置项值 */
    @Excel(name = "配置项值")
    private String rainbowValue;

    /** 描述 */
    @Excel(name = "描述")
    private String cellDesc;

    /** 行号 */
    @Excel(name = "行号")
    private Integer lineNum;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private Integer deleted;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createUser;

    /** 修改人 */
    @Excel(name = "修改人")
    private String updateUser;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setGroupId(Long groupId) 
    {
        this.groupId = groupId;
    }

    public Long getGroupId() 
    {
        return groupId;
    }
    public void setRainbowKey(String rainbowKey) 
    {
        this.rainbowKey = rainbowKey;
    }

    public String getRainbowKey() 
    {
        return rainbowKey;
    }
    public void setRainbowValue(String rainbowValue) 
    {
        this.rainbowValue = rainbowValue;
    }

    public String getRainbowValue() 
    {
        return rainbowValue;
    }
    public void setCellDesc(String cellDesc) 
    {
        this.cellDesc = cellDesc;
    }

    public String getCellDesc() 
    {
        return cellDesc;
    }
    public void setLineNum(Integer lineNum) 
    {
        this.lineNum = lineNum;
    }

    public Integer getLineNum() 
    {
        return lineNum;
    }
    public void setDeleted(Integer deleted) 
    {
        this.deleted = deleted;
    }

    public Integer getDeleted() 
    {
        return deleted;
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
            .append("groupId", getGroupId())
            .append("rainbowKey", getRainbowKey())
            .append("rainbowValue", getRainbowValue())
            .append("cellDesc", getCellDesc())
            .append("lineNum", getLineNum())
            .append("deleted", getDeleted())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createUser", getCreateUser())
            .append("updateUser", getUpdateUser())
            .toString();
    }
}
