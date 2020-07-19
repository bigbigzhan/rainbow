package com.rainbow.web.controller.system;

import java.util.List;
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
import com.rainbow.system.domain.RainbowEnvironment;
import com.rainbow.system.service.IRainbowEnvironmentService;
import com.rainbow.common.core.controller.BaseController;
import com.rainbow.common.core.domain.AjaxResult;
import com.rainbow.common.utils.poi.ExcelUtil;
import com.rainbow.common.core.page.TableDataInfo;

/**
 * 环境信息Controller
 * 
 * @author Gz
 * @date 2020-07-04
 */
@Controller
@RequestMapping("/system/environment")
public class RainbowEnvironmentController extends BaseController
{
    private String prefix = "system/environment";

    @Autowired
    private IRainbowEnvironmentService rainbowEnvironmentService;

    @RequiresPermissions("system:environment:view")
    @GetMapping()
    public String environment()
    {
        return prefix + "/environment";
    }

    /**
     * 查询环境信息列表
     */
    @RequiresPermissions("system:environment:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(RainbowEnvironment rainbowEnvironment)
    {
        startPage();
        List<RainbowEnvironment> list = rainbowEnvironmentService.selectRainbowEnvironmentList(rainbowEnvironment);
        return getDataTable(list);
    }

    /**
     * 导出环境信息列表
     */
    @RequiresPermissions("system:environment:export")
    @Log(title = "环境信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(RainbowEnvironment rainbowEnvironment)
    {
        List<RainbowEnvironment> list = rainbowEnvironmentService.selectRainbowEnvironmentList(rainbowEnvironment);
        ExcelUtil<RainbowEnvironment> util = new ExcelUtil<RainbowEnvironment>(RainbowEnvironment.class);
        return util.exportExcel(list, "environment");
    }

    /**
     * 新增环境信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存环境信息
     */
    @RequiresPermissions("system:environment:add")
    @Log(title = "环境信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(RainbowEnvironment rainbowEnvironment)
    {
        return toAjax(rainbowEnvironmentService.insertRainbowEnvironment(rainbowEnvironment));
    }

    /**
     * 修改环境信息
     */
    @GetMapping("/edit/{env}")
    public String edit(@PathVariable("env") String env, ModelMap mmap)
    {
        RainbowEnvironment rainbowEnvironment = rainbowEnvironmentService.selectRainbowEnvironmentById(env);
        mmap.put("rainbowEnvironment", rainbowEnvironment);
        return prefix + "/edit";
    }

    /**
     * 修改保存环境信息
     */
    @RequiresPermissions("system:environment:edit")
    @Log(title = "环境信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(RainbowEnvironment rainbowEnvironment)
    {
        return toAjax(rainbowEnvironmentService.updateRainbowEnvironment(rainbowEnvironment));
    }

    /**
     * 删除环境信息
     */
    @RequiresPermissions("system:environment:remove")
    @Log(title = "环境信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(rainbowEnvironmentService.deleteRainbowEnvironmentByIds(ids));
    }
}
