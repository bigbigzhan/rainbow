package com.rainbow.web.controller.system;

import java.util.List;

import com.rainbow.system.domain.RainbowGroups;
import com.rainbow.system.service.IRainbowGroupsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.rainbow.common.annotation.Log;
import com.rainbow.common.enums.BusinessType;
import com.rainbow.system.domain.RainbowCells;
import com.rainbow.system.service.IRainbowCellsService;
import com.rainbow.common.core.controller.BaseController;
import com.rainbow.common.core.domain.AjaxResult;
import com.rainbow.common.utils.poi.ExcelUtil;
import com.rainbow.common.core.page.TableDataInfo;

/**
 * 配置项目Controller
 * 
 * @author Gz
 * @date 2020-07-04
 */
@Controller
@RequestMapping("/system/cells")
public class RainbowCellsController extends BaseController
{
    private String prefix = "system/cells";

    @Autowired
    private IRainbowCellsService rainbowCellsService;
    @Autowired
    private IRainbowGroupsService rainbowGroupsService;

    @RequiresPermissions("system:cells:view")
    @GetMapping()
    public String cells()
    {
        return prefix + "/cells";
    }

    /**
     * 查询配置项目列表
     */
    @RequiresPermissions("system:cells:list")
    @RequestMapping("/list/{groupId}")
    @ResponseBody
    public TableDataInfo list( Long groupId) throws Exception {
        if(null == groupId){
            throw new Exception("groupId must not null");
        }
        RainbowCells rainbowCells = new RainbowCells();
        rainbowCells.setGroupId(groupId);
        startPage();
        List<RainbowCells> list = rainbowCellsService.selectRainbowCellsList(rainbowCells);
        return getDataTable(list);
    }

    /**
     * 导出配置项目列表
     */
    @RequiresPermissions("system:cells:export")
    @Log(title = "配置项目", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(RainbowCells rainbowCells)
    {
        List<RainbowCells> list = rainbowCellsService.selectRainbowCellsList(rainbowCells);
        ExcelUtil<RainbowCells> util = new ExcelUtil<RainbowCells>(RainbowCells.class);
        return util.exportExcel(list, "cells");
    }

    /**
     * 新增配置项目
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存配置项目
     */
    @RequiresPermissions("system:cells:add")
    @Log(title = "配置项目", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(RainbowCells rainbowCells)
    {
        Integer maxLineNo = rainbowCellsService.selectLineMaxNoBygroupId(rainbowCells.getGroupId());
        if(maxLineNo == null){
            maxLineNo = 0;
            RainbowGroups rainbowGroups = rainbowGroupsService.selectRainbowGroupsById(rainbowCells.getGroupId());
            rainbowCells.setGroupName(rainbowGroups.getGroupName());
        }
        rainbowCells.setLineNum(maxLineNo + 1);
        return toAjax(rainbowCellsService.insertRainbowCells(rainbowCells));
    }

    /**
     * 修改配置项目
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        RainbowCells rainbowCells = rainbowCellsService.selectRainbowCellsById(id);
        mmap.put("rainbowCells", rainbowCells);
        return prefix + "/edit";
    }

    /**
     * 修改保存配置项目
     */
    @RequiresPermissions("system:cells:edit")
    @Log(title = "配置项目", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(RainbowCells rainbowCells)
    {
        return toAjax(rainbowCellsService.updateRainbowCells(rainbowCells));
    }

    /**
     * 删除配置项目
     */
    @RequiresPermissions("system:cells:remove")
    @Log(title = "配置项目", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(rainbowCellsService.deleteRainbowCellsByIds(ids));
    }
}
