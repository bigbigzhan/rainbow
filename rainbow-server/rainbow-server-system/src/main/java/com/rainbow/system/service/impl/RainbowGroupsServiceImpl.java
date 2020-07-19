package com.rainbow.system.service.impl;

import java.util.List;
import com.rainbow.common.utils.DateUtils;
import com.rainbow.system.service.IRainbowGroupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rainbow.system.mapper.RainbowGroupsMapper;
import com.rainbow.system.domain.RainbowGroups;
import com.rainbow.common.core.text.Convert;

/**
 * rainbow配置组Service业务层处理
 * 
 * @author Gz
 * @date 2020-07-04
 */
@Service
public class RainbowGroupsServiceImpl implements IRainbowGroupsService
{
    @Autowired
    private RainbowGroupsMapper rainbowGroupsMapper;

    /**
     * 查询rainbow配置组
     * 
     * @param id rainbow配置组ID
     * @return rainbow配置组
     */
    @Override
    public RainbowGroups selectRainbowGroupsById(Long id)
    {
        return rainbowGroupsMapper.selectRainbowGroupsById(id);
    }

    /**
     * 查询rainbow配置组列表
     * 
     * @param rainbowGroups rainbow配置组
     * @return rainbow配置组
     */
    @Override
    public List<RainbowGroups> selectRainbowGroupsList(RainbowGroups rainbowGroups)
    {
        return rainbowGroupsMapper.selectRainbowGroupsList(rainbowGroups);
    }

    /**
     * 新增rainbow配置组
     * 
     * @param rainbowGroups rainbow配置组
     * @return 结果
     */
    @Override
    public int insertRainbowGroups(RainbowGroups rainbowGroups)
    {
        rainbowGroups.setCreateTime(DateUtils.getNowDate());
        return rainbowGroupsMapper.insertRainbowGroups(rainbowGroups);
    }

    /**
     * 修改rainbow配置组
     * 
     * @param rainbowGroups rainbow配置组
     * @return 结果
     */
    @Override
    public int updateRainbowGroups(RainbowGroups rainbowGroups)
    {
        rainbowGroups.setUpdateTime(DateUtils.getNowDate());
        return rainbowGroupsMapper.updateRainbowGroups(rainbowGroups);
    }

    /**
     * 删除rainbow配置组对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteRainbowGroupsByIds(String ids)
    {
        return rainbowGroupsMapper.deleteRainbowGroupsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除rainbow配置组信息
     * 
     * @param id rainbow配置组ID
     * @return 结果
     */
    @Override
    public int deleteRainbowGroupsById(Long id)
    {
        return rainbowGroupsMapper.deleteRainbowGroupsById(id);
    }

    @Override
    public List<Long> queryGrpupIds(String appName, String envName, List<String> groupNames) {

        return rainbowGroupsMapper.queryGrpupIds(appName,envName,groupNames);
    }
}
