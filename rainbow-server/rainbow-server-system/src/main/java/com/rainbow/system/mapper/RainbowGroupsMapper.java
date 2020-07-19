package com.rainbow.system.mapper;

import java.util.List;

import com.rainbow.system.domain.RainbowGroups;
import org.apache.ibatis.annotations.Param;

/**
 * rainbow配置组Mapper接口
 * 
 * @author Gz
 * @date 2020-07-04
 */
public interface RainbowGroupsMapper 
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
     * 删除rainbow配置组
     * 
     * @param id rainbow配置组ID
     * @return 结果
     */
    public int deleteRainbowGroupsById(Long id);

    /**
     * 批量删除rainbow配置组
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRainbowGroupsByIds(String[] ids);

    List<Long> queryGrpupIds(@Param("appName") String appName,@Param("envName") String envName, @Param("groupNames") List<String> groupNames);
}
