package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.RainbowEnvironmentMapper;
import com.ruoyi.system.domain.RainbowEnvironment;
import com.ruoyi.system.service.IRainbowEnvironmentService;
import com.ruoyi.common.core.text.Convert;

/**
 * 环境信息Service业务层处理
 * 
 * @author Gz
 * @date 2020-07-04
 */
@Service
public class RainbowEnvironmentServiceImpl implements IRainbowEnvironmentService 
{
    @Autowired
    private RainbowEnvironmentMapper rainbowEnvironmentMapper;

    /**
     * 查询环境信息
     * 
     * @param env 环境信息ID
     * @return 环境信息
     */
    @Override
    public RainbowEnvironment selectRainbowEnvironmentById(String env)
    {
        return rainbowEnvironmentMapper.selectRainbowEnvironmentById(env);
    }

    /**
     * 查询环境信息列表
     * 
     * @param rainbowEnvironment 环境信息
     * @return 环境信息
     */
    @Override
    public List<RainbowEnvironment> selectRainbowEnvironmentList(RainbowEnvironment rainbowEnvironment)
    {
        return rainbowEnvironmentMapper.selectRainbowEnvironmentList(rainbowEnvironment);
    }

    /**
     * 新增环境信息
     * 
     * @param rainbowEnvironment 环境信息
     * @return 结果
     */
    @Override
    public int insertRainbowEnvironment(RainbowEnvironment rainbowEnvironment)
    {
        return rainbowEnvironmentMapper.insertRainbowEnvironment(rainbowEnvironment);
    }

    /**
     * 修改环境信息
     * 
     * @param rainbowEnvironment 环境信息
     * @return 结果
     */
    @Override
    public int updateRainbowEnvironment(RainbowEnvironment rainbowEnvironment)
    {
        return rainbowEnvironmentMapper.updateRainbowEnvironment(rainbowEnvironment);
    }

    /**
     * 删除环境信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteRainbowEnvironmentByIds(String ids)
    {
        return rainbowEnvironmentMapper.deleteRainbowEnvironmentByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除环境信息信息
     * 
     * @param env 环境信息ID
     * @return 结果
     */
    @Override
    public int deleteRainbowEnvironmentById(String env)
    {
        return rainbowEnvironmentMapper.deleteRainbowEnvironmentById(env);
    }
}
