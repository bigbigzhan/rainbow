package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.RainbowApplication;

/**
 * 应用信息Mapper接口
 * 
 * @author Gz
 * @date 2020-07-04
 */
public interface RainbowApplicationMapper 
{
    /**
     * 查询应用信息
     * 
     * @param appName 应用信息ID
     * @return 应用信息
     */
    public RainbowApplication selectRainbowApplicationById(String appName);

    /**
     * 查询应用信息列表
     * 
     * @param rainbowApplication 应用信息
     * @return 应用信息集合
     */
    public List<RainbowApplication> selectRainbowApplicationList(RainbowApplication rainbowApplication);

    /**
     * 新增应用信息
     * 
     * @param rainbowApplication 应用信息
     * @return 结果
     */
    public int insertRainbowApplication(RainbowApplication rainbowApplication);

    /**
     * 修改应用信息
     * 
     * @param rainbowApplication 应用信息
     * @return 结果
     */
    public int updateRainbowApplication(RainbowApplication rainbowApplication);

    /**
     * 删除应用信息
     * 
     * @param appName 应用信息ID
     * @return 结果
     */
    public int deleteRainbowApplicationById(String appName);

    /**
     * 批量删除应用信息
     * 
     * @param appNames 需要删除的数据ID
     * @return 结果
     */
    public int deleteRainbowApplicationByIds(String[] appNames);
}
