package com.rainbow.system.service.impl;

import java.util.List;

import com.rainbow.system.mapper.RainbowApplicationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rainbow.system.domain.RainbowApplication;
import com.rainbow.system.service.IRainbowApplicationService;
import com.rainbow.common.core.text.Convert;

/**
 * 应用信息Service业务层处理
 * 
 * @author Gz
 * @date 2020-07-04
 */
@Service
public class RainbowApplicationServiceImpl implements IRainbowApplicationService 
{
    @Autowired
    private RainbowApplicationMapper rainbowApplicationMapper;

    /**
     * 查询应用信息
     * 
     * @param appName 应用信息ID
     * @return 应用信息
     */
    @Override
    public RainbowApplication selectRainbowApplicationById(String appName)
    {
        return rainbowApplicationMapper.selectRainbowApplicationById(appName);
    }

    /**
     * 查询应用信息列表
     * 
     * @param rainbowApplication 应用信息
     * @return 应用信息
     */
    @Override
    public List<RainbowApplication> selectRainbowApplicationList(RainbowApplication rainbowApplication)
    {
        return rainbowApplicationMapper.selectRainbowApplicationList(rainbowApplication);
    }

    /**
     * 新增应用信息
     * 
     * @param rainbowApplication 应用信息
     * @return 结果
     */
    @Override
    public int insertRainbowApplication(RainbowApplication rainbowApplication)
    {
        return rainbowApplicationMapper.insertRainbowApplication(rainbowApplication);
    }

    /**
     * 修改应用信息
     * 
     * @param rainbowApplication 应用信息
     * @return 结果
     */
    @Override
    public int updateRainbowApplication(RainbowApplication rainbowApplication)
    {
        return rainbowApplicationMapper.updateRainbowApplication(rainbowApplication);
    }

    /**
     * 删除应用信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteRainbowApplicationByIds(String ids)
    {
        return rainbowApplicationMapper.deleteRainbowApplicationByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除应用信息信息
     * 
     * @param appName 应用信息ID
     * @return 结果
     */
    @Override
    public int deleteRainbowApplicationById(String appName)
    {
        return rainbowApplicationMapper.deleteRainbowApplicationById(appName);
    }
}
