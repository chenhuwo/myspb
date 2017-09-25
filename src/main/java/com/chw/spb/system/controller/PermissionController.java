package com.chw.spb.system.controller;

import com.chw.spb.system.entity.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chw.spb.common.UserUtil;
import com.chw.spb.system.service.PermissionService;

import java.util.List;

/**
 * 
 * @author chw
 *
 */
@Controller
@RequestMapping("/system/permission")
public class PermissionController {
	
	@Autowired
	private PermissionService permissionService;
	
	/**
	 * 获取当前用户登陆的权限集合
	 * @return
	 */
	@RequestMapping("/getCurrent")
	@ResponseBody
	public Object getCurrentUserPermission(){
		return permissionService.getCurrentUserPermission(UserUtil.getCurrentShiroUser().getId()+"");
	}

	@ResponseBody
	@RequestMapping("/list")
	public Object listAllPermission() {
		return permissionService.listAll();
	}


	
}
