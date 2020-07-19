package com.rainbow.system.service.impl;

import java.util.List;
import com.rainbow.common.utils.DateUtils;
import com.rainbow.system.mapper.RainbowCellsMapper;
import com.rainbow.system.service.IRainbowCellsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rainbow.system.domain.RainbowCells;
import com.rainbow.common.core.text.Convert;

/**
 * 配置项目Service业务层处理
 * 
 * @author Gz
 * @date 2020-07-04
 */
@Service
public class RainbowCellsServiceImpl implements IRainbowCellsService
{
    @Autowired
    private RainbowCellsMapper rainbowCellsMapper;

    /**
     * 查询配置项目
     * 
     * @param id 配置项目ID
     * @return 配置项目
     */
    @Override
    public RainbowCells selectRainbowCellsById(Long id)
    {
        return rainbowCellsMapper.selectRainbowCellsById(id);
    }

    @Override
    public Integer selectLineMaxNoBygroupId(Long groupId) {
        return rainbowCellsMapper.selectLineMaxNoBygroupId(groupId);
    }

    /**
     * 查询配置项目列表
     * 
     * @param rainbowCells 配置项目
     * @return 配置项目
     */
    @Override
    public List<RainbowCells> selectRainbowCellsList(RainbowCells rainbowCells)
    {
        return rainbowCellsMapper.selectRainbowCellsList(rainbowCells);
    }

    /**
     * 新增配置项目
     * 
     * @param rainbowCells 配置项目
     * @return 结果
     */
    @Override
    public int insertRainbowCells(RainbowCells rainbowCells)
    {
        rainbowCells.setCreateTime(DateUtils.getNowDate());
        return rainbowCellsMapper.insertRainbowCells(rainbowCells);
    }

    /**
     * 修改配置项目
     * 
     * @param rainbowCells 配置项目
     * @return 结果
     */
    @Override
    public int updateRainbowCells(RainbowCells rainbowCells)
    {
        rainbowCells.setUpdateTime(DateUtils.getNowDate());
        return rainbowCellsMapper.updateRainbowCells(rainbowCells);
    }

    /**
     * 删除配置项目对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteRainbowCellsByIds(String ids)
    {
        return rainbowCellsMapper.deleteRainbowCellsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除配置项目信息
     * 
     * @param id 配置项目ID
     * @return 结果
     */
    @Override
    public int deleteRainbowCellsById(Long id)
    {
        return rainbowCellsMapper.deleteRainbowCellsById(id);
    }

    @Override
    public List<RainbowCells> queryCellsByGroups(String envName, String appName, List<String> groupNames) {
        return rainbowCellsMapper.queryCellsByGroups(envName,appName,groupNames);
    }

    @Override
    public List<RainbowCells> queryCellsByGroupId(Long groupId) {
        return rainbowCellsMapper.queryCellsByGroupId(groupId);

    }
}
