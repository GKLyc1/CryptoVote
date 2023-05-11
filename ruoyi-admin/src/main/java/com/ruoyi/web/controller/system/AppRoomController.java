package com.ruoyi.web.controller.system;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.KamUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.AppRoom;
import com.ruoyi.system.service.IAppRoomService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 房间大厅Controller
 * 
 * @author ChrisGai
 * @date 2023-05-11
 */
@Controller
@RequestMapping("/system/room")
public class AppRoomController extends BaseController
{
    private String prefix = "system/room";

    @Autowired
    private IAppRoomService appRoomService;

    @RequiresPermissions("system:room:view")
    @GetMapping()
    public String room()
    {
        return prefix + "/room";
    }


    // 创建房间
    @PostMapping("/addroom")
    @ResponseBody
    public String addRoom(AppRoom appRoom)
    {
        try {
//            roomTitle
            String romtitle = appRoom.getRoomTitle();
            int flag_rt = appRoomService.selectcountroomtitle(romtitle);
            if (flag_rt > 0) {
                return KamUtil.returnError("此房间名称以创建");
            }
            int flag = appRoomService.insertAppRoom(appRoom);

            if (flag>0){
                String roomid = appRoomService.selectroomid(romtitle);
                return KamUtil.returnSuccess(roomid);
            }
            return KamUtil.returnError();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return KamUtil.returnError();
    }

    /**
     * 房间列表
     * @param appRoom
     * @return
     */
    @PostMapping("/roomlist")
    @ResponseBody
    public String roomlist(AppRoom appRoom)
    {
        try {
            List<AppRoom> list = appRoomService.selectAppRoomList(appRoom);

            return KamUtil.returnSuccess(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return KamUtil.returnError();
    }





    /**
     * 查询房间大厅列表
     */
    @RequiresPermissions("system:room:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AppRoom appRoom)
    {
        startPage();
        List<AppRoom> list = appRoomService.selectAppRoomList(appRoom);
        return getDataTable(list);
    }

    /**
     * 导出房间大厅列表
     */
    @RequiresPermissions("system:room:export")
    @Log(title = "房间大厅", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AppRoom appRoom)
    {
        List<AppRoom> list = appRoomService.selectAppRoomList(appRoom);
        ExcelUtil<AppRoom> util = new ExcelUtil<AppRoom>(AppRoom.class);
        return util.exportExcel(list, "房间大厅数据");
    }

    /**
     * 新增房间大厅
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存房间大厅
     */
    @RequiresPermissions("system:room:add")
    @Log(title = "房间大厅", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AppRoom appRoom)
    {
        return toAjax(appRoomService.insertAppRoom(appRoom));
    }

    /**
     * 修改房间大厅
     */
    @RequiresPermissions("system:room:edit")
    @GetMapping("/edit/{roomId}")
    public String edit(@PathVariable("roomId") Long roomId, ModelMap mmap)
    {
        AppRoom appRoom = appRoomService.selectAppRoomByRoomId(roomId);
        mmap.put("appRoom", appRoom);
        return prefix + "/edit";
    }

    /**
     * 修改保存房间大厅
     */
    @RequiresPermissions("system:room:edit")
    @Log(title = "房间大厅", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AppRoom appRoom)
    {
        return toAjax(appRoomService.updateAppRoom(appRoom));
    }

    /**
     * 删除房间大厅
     */
    @RequiresPermissions("system:room:remove")
    @Log(title = "房间大厅", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(appRoomService.deleteAppRoomByRoomIds(ids));
    }
}
