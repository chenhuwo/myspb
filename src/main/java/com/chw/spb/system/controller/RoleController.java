package com.chw.spb.system.controller;

import com.chw.spb.common.EasyPage;
import com.chw.spb.common.PageUtil;
import com.chw.spb.common.ResponseResult;
import com.chw.spb.system.entity.Permission;
import com.chw.spb.system.entity.Role;
import com.chw.spb.system.service.RoleService;
import com.chw.spb.system.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
* @Author chw
* @Date 2017/9/19
* @Description
*/
@Controller
@RequestMapping("/system/role")
public class RoleController {

    private static final Logger log = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @RequestMapping("/all")
    @ResponseBody
    public Object listRoleDatagrid(HttpServletRequest request){
        EasyPage page = new EasyPage(Role.class).build(request);
        Page<Object> startPage = PageHelper.startPage(page.getPageNumber(), page.getPageSize());
        roleService.listRole(page);
        return PageUtil.createDatagrid(startPage.getTotal(), startPage.getResult());
    }

    /**
     * 默认页面
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list() {
        return "/system/role/roleList.html";
    }


    @RequestMapping("/getPermisson/{roleId}")
    @ResponseBody
    public List<Permission> listPermissionByRoleId(@PathVariable("roleId") Integer roleId) {
        return roleService.listPermissionByRoleId(roleId);
    }


    @RequestMapping("/updateRolePermisson")
    @ResponseBody
    public Object listPermissionByRoleId(@RequestParam("roleId") Integer roleId, @RequestParam("permIds[]") List<Integer> permIds) {
        try {
            roleService.updateRolePermission(roleId,permIds);
        } catch (Exception e) {
            log.error("",e);
            return ResponseResult.failedMsg("更新失败");
        }
        return ResponseResult.successMsg("更新成功");
    }


}



