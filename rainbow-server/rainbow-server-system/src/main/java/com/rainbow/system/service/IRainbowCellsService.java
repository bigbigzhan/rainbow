package com.rainbow.system.service;

import java.util.List;
import com.rainbow.system.domain.RainbowCells;

/**
 * 配置项目Service接口
 * 
 * @author Gz
 * @date 2020-07-04
 */
public interface IRainbowCellsService 
{
    /**
     * 查询配置项目
     * 
     * @param id 配置项目ID
     * @return 配置项目
     */
    public RainbowCells selectRainbowCellsById(Long id);

    public Integer selectLineMaxNoBygroupId(Long groupId);

    /**
     * 查询配置项目列表
     * 
     * @param rainbowCells 配置项目
     * @return 配置项目集合
     */
    public List<RainbowCells> selectRainbowCellsList(RainbowCells rainbowCells);

    /**
     * 新增配置项目
     * 
     * @param rainbowCells 配置项目
     * @return 结果
     */
    public int insertRainbowCells(RainbowCells rainbowCells);

    /**
     * 修改配置项目
     * 
     * @param rainbowCells 配置项目
     * @return 结果
     */
    public int updateRainbowCells(RainbowCells rainbowCells);

    /**
     * 批量删除配置项目
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRainbowCellsByIds(String ids);

    /**
     * 删除配置项目信息
     * 
     * @param id 配置项目ID
     * @return 结果
     */
    public int deleteRainbowCellsById(Long id);

    List<RainbowCells> queryCellsByGroups(String envName,String appName,List<String> groupNames);

    List<RainbowCells> queryCellsByGroupId(Long groupId);

}
