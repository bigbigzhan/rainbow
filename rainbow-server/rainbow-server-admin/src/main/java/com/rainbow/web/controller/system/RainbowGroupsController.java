package com.rainbow.web.controller.system;

import java.util.List;

import com.rainbow.framework.util.ShiroUtils;
import com.rainbow.web.netty.NettyServerService;
import com.rainbow.web.netty.SendConfigType;
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
import com.rainbow.system.domain.RainbowGroups;
import com.rainbow.system.service.IRainbowGroupsService;
import com.rainbow.common.core.controller.BaseController;
import com.rainbow.common.core.domain.AjaxResult;
import com.rainbow.common.utils.poi.ExcelUtil;
import com.rainbow.common.core.page.TableDataInfo;

/**
 * rainbow配置组Controller
 * 
 * @author Gz
 * @date 2020-07-04
 */
@Controller
@RequestMapping("/system/groups")
public class RainbowGroupsController extends BaseController
{
    private String prefix = "system/groups";

    @Autowired
    private IRainbowGroupsService rainbowGroupsService;

    @Autowired
    private NettyServerService nettyServerService;

    @RequiresPermissions("system:groups:view")
    @GetMapping()
    public String groups()
    {
        return prefix + "/groups";
    }

    /**
     * 查询rainbow配置组列表
     */
    @RequiresPermissions("system:groups:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(RainbowGroups rainbowGroups)
    {
        startPage();
        List<RainbowGroups> list = rainbowGroupsService.selectRainbowGroupsList(rainbowGroups);
        return getDataTable(list);
    }

    /**
     * 导出rainbow配置组列表
     */
    @RequiresPermissions("system:groups:export")
    @Log(title = "rainbow配置组", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(RainbowGroups rainbowGroups)
    {
        List<RainbowGroups> list = rainbowGroupsService.selectRainbowGroupsList(rainbowGroups);
        ExcelUtil<RainbowGroups> util = new ExcelUtil<RainbowGroups>(RainbowGroups.class);
        return util.exportExcel(list, "groups");
    }

    /**
     * 新增rainbow配置组
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存rainbow配置组
     */
    @RequiresPermissions("system:groups:add")
    @Log(title = "rainbow配置组", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(RainbowGroups rainbowGroups)
    {
        rainbowGroups.setCreateUser(ShiroUtils.getLoginName());
        return toAjax(rainbowGroupsService.insertRainbowGroups(rainbowGroups));
    }

    /**
     * 修改rainbow配置组
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        RainbowGroups rainbowGroups = rainbowGroupsService.selectRainbowGroupsById(id);
        mmap.put("rainbowGroups", rainbowGroups);
        return prefix + "/edit";
    }

    @RequiresPermissions("system:groups:push")
    @Log(title = "rainbow配置组", businessType = BusinessType.OTHER)
    @PostMapping("/push/{groupId}")
    @ResponseBody
    public AjaxResult push(@PathVariable("groupId") Long groupId)
    {
        RainbowGroups rainbowGroups = rainbowGroupsService.selectRainbowGroupsById(groupId);
        StringBuilder sb = new StringBuilder()
                .append(rainbowGroups.getEnv())
                .append("#")
                .append(rainbowGroups.getAppName())
                .append("#")
                .append(rainbowGroups.getGroupName());
        String key = sb.toString();
        nettyServerService.sendConfig(groupId, SendConfigType.EDIT,key);
        return toAjax(1);
    }

    /**
     * 查询字典详细
     */
    @RequiresPermissions("system:dict:list")
    @RequestMapping("/detail/{groupId}")
    public String detail(@PathVariable("groupId") Long groupId, ModelMap mmap) {
        mmap.put("groupId", groupId);
        return "system/cells/cells";
    }

    /**
     * 修改保存rainbow配置组
     */
    @RequiresPermissions("system:groups:edit")
    @Log(title = "rainbow配置组", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(RainbowGroups rainbowGroups)
    {
        rainbowGroups.setUpdateUser(ShiroUtils.getLoginName());
        return toAjax(rainbowGroupsService.updateRainbowGroups(rainbowGroups));
    }

    /**
     * 删除rainbow配置组
     */
    @RequiresPermissions("system:groups:remove")
    @Log(title = "rainbow配置组", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(rainbowGroupsService.deleteRainbowGroupsByIds(ids));
    }
}
