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
import com.rainbow.system.domain.RainbowApplication;
import com.rainbow.system.service.IRainbowApplicationService;
import com.rainbow.common.core.controller.BaseController;
import com.rainbow.common.core.domain.AjaxResult;
import com.rainbow.common.utils.poi.ExcelUtil;
import com.rainbow.common.core.page.TableDataInfo;

/**
 * 应用信息Controller
 * 
 * @author Gz
 * @date 2020-07-04
 */
@Controller
@RequestMapping("/system/application")
public class RainbowApplicationController extends BaseController
{
    private String prefix = "system/application";

    @Autowired
    private IRainbowApplicationService rainbowApplicationService;

    @RequiresPermissions("system:application:view")
    @GetMapping()
    public String application()
    {
        return prefix + "/application";
    }

    /**
     * 查询应用信息列表
     */
    @RequiresPermissions("system:application:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(RainbowApplication rainbowApplication)
    {
        startPage();
        List<RainbowApplication> list = rainbowApplicationService.selectRainbowApplicationList(rainbowApplication);
        return getDataTable(list);
    }

    /**
     * 导出应用信息列表
     */
    @RequiresPermissions("system:application:export")
    @Log(title = "应用信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(RainbowApplication rainbowApplication)
    {
        List<RainbowApplication> list = rainbowApplicationService.selectRainbowApplicationList(rainbowApplication);
        ExcelUtil<RainbowApplication> util = new ExcelUtil<RainbowApplication>(RainbowApplication.class);
        return util.exportExcel(list, "application");
    }

    /**
     * 新增应用信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存应用信息
     */
    @RequiresPermissions("system:application:add")
    @Log(title = "应用信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(RainbowApplication rainbowApplication)
    {
        return toAjax(rainbowApplicationService.insertRainbowApplication(rainbowApplication));
    }

    /**
     * 修改应用信息
     */
    @GetMapping("/edit/{appName}")
    public String edit(@PathVariable("appName") String appName, ModelMap mmap)
    {
        RainbowApplication rainbowApplication = rainbowApplicationService.selectRainbowApplicationById(appName);
        mmap.put("rainbowApplication", rainbowApplication);
        return prefix + "/edit";
    }

    /**
     * 修改保存应用信息
     */
    @RequiresPermissions("system:application:edit")
    @Log(title = "应用信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(RainbowApplication rainbowApplication)
    {
        return toAjax(rainbowApplicationService.updateRainbowApplication(rainbowApplication));
    }

    /**
     * 删除应用信息
     */
    @RequiresPermissions("system:application:remove")
    @Log(title = "应用信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(rainbowApplicationService.deleteRainbowApplicationByIds(ids));
    }
}
