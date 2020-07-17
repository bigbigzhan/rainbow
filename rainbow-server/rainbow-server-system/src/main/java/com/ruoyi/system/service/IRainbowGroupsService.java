package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.RainbowGroups;

/**
 * rainbow配置组Service接口
 * 
 * @author Gz
 * @date 2020-07-04
 */
public interface IRainbowGroupsService 
{
    /**
     * 查询rainbow配置组
     * 
     * @param id rainbow配置组ID
     * @return rainbow配置组
     */
    public RainbowGroups selectRainbowGroupsById(Long id);

    /**
     * 查询rainbow配置组列表
     * 
     * @param rainbowGroups rainbow配置组
     * @return rainbow配置组集合
     */
    public List<RainbowGroups> selectRainbowGroupsList(RainbowGroups rainbowGroups);

    /**
     * 新增rainbow配置组
     * 
     * @param rainbowGroups rainbow配置组
     * @return 结果
     */
    public int insertRainbowGroups(RainbowGroups rainbowGroups);

    /**
     * 修改rainbow配置组
     * 
     * @param rainbowGroups rainbow配置组
     * @return 结果
     */
    public int updateRainbowGroups(RainbowGroups rainbowGroups);

    /**
     * 批量删除rainbow配置组
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRainbowGroupsByIds(String ids);

    /**
     * 删除rainbow配置组信息
     * 
     * @param id rainbow配置组ID
     * @return 结果
     */
    public int deleteRainbowGroupsById(Long id);

    List<Long> queryGrpupIds(String appName,String envName,List<String> groupNames);
}
