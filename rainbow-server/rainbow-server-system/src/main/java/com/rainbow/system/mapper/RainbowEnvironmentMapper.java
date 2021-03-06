package com.rainbow.system.mapper;

import java.util.List;
import com.rainbow.system.domain.RainbowEnvironment;

/**
 * 环境信息Mapper接口
 * 
 * @author Gz
 * @date 2020-07-04
 */
public interface RainbowEnvironmentMapper 
{
    /**
     * 查询环境信息
     * 
     * @param env 环境信息ID
     * @return 环境信息
     */
    public RainbowEnvironment selectRainbowEnvironmentById(String env);

    /**
     * 查询环境信息列表
     * 
     * @param rainbowEnvironment 环境信息
     * @return 环境信息集合
     */
    public List<RainbowEnvironment> selectRainbowEnvironmentList(RainbowEnvironment rainbowEnvironment);

    /**
     * 新增环境信息
     * 
     * @param rainbowEnvironment 环境信息
     * @return 结果
     */
    public int insertRainbowEnvironment(RainbowEnvironment rainbowEnvironment);

    /**
     * 修改环境信息
     * 
     * @param rainbowEnvironment 环境信息
     * @return 结果
     */
    public int updateRainbowEnvironment(RainbowEnvironment rainbowEnvironment);

    /**
     * 删除环境信息
     * 
     * @param env 环境信息ID
     * @return 结果
     */
    public int deleteRainbowEnvironmentById(String env);

    /**
     * 批量删除环境信息
     * 
     * @param envs 需要删除的数据ID
     * @return 结果
     */
    public int deleteRainbowEnvironmentByIds(String[] envs);
}
