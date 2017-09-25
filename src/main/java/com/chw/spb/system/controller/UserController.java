package com.chw.spb.system.controller;

import com.chw.spb.common.EasyPage;
import com.chw.spb.common.PageUtil;
import com.chw.spb.common.ResponseResult;
import com.chw.spb.common.UserUtil;
import com.chw.spb.system.entity.User;
import com.chw.spb.system.service.UserRoleService;
import com.chw.spb.system.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.Cacheable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 
 * @author chw
 *
 */
@Controller
@RequestMapping("/system/user")
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;

	@Autowired
	private UserRoleService userRoleService;


	/**
	 * 默认页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list() {
		return "/system/user/userList.html";
	}

	/**
	 * 获取用户json
	 */
	//@RequiresPermissions("sys:user:view")
	@RequestMapping(value="datagrid",method = RequestMethod.GET)
	@ResponseBody
	public Object getData(HttpServletRequest request,Integer pageNumber,Integer pageSize) {
		EasyPage easyPage = new EasyPage(User.class).build(request);
		Page<User> startPage = PageHelper.startPage(pageNumber,pageSize);
		userService.listUser(easyPage);
		return PageUtil.createDatagrid(startPage.getTotal(), startPage.getResult());
	}

	@RequestMapping("/tocreate")
	public ModelAndView create() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/system/user/userForm.html");
		modelAndView.addObject("action","create");
		modelAndView.addObject("user",new User());
		return modelAndView;
	}

	@RequestMapping("/toUpdate/{id}")
	public ModelAndView update(@PathVariable("id")String id) {
		if (StringUtils.isEmpty(id)) {
			return new ModelAndView();
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/system/user/userForm.html");
		modelAndView.addObject("action","update");
		//查询该用户
		User user = userService.findUserByUserId(id);
		modelAndView.addObject("user",user);
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping("/find")
	public User find(String userId) {
		return userService.findUserByUserId(userId);
	}

	/**
	 * Ajax请求校验loginName是否唯一。
	 */
	@RequestMapping(value = "checkLoginName")
	@ResponseBody
	public String checkLoginName(String loginName) {
		if (userService.findUserByUsername(loginName) == null) {
			return "true";
		} else {
			return "false";
		}
	}

	@RequestMapping(value = "/create",method = RequestMethod.POST)
	@ResponseBody
	public Object add(User user) {
		int res = userService.save(user);
		if (res < 1) {

			return ResponseResult.failedMsg("失败");
		} else {
			return ResponseResult.successMsg("成功");
		}
	}

	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@ResponseBody
	public Object update(User user) {
		int res = userService.update(user);
		if (res < 1) {

			return ResponseResult.failedMsg("失败");
		} else {
			return ResponseResult.successMsg("成功");
		}
	}

	@RequestMapping("/delete/{id}")
	@ResponseBody
	public Object delete(@PathVariable("id") String id) {
		if (StringUtils.isEmpty(id)) {
			return ResponseResult.failedMsg("id不能为空");
		}
		int result = userService.deleteById(id);
		if (result < 1) {
			return ResponseResult.failedMsg("删除失败");
		} else {
			return ResponseResult.successMsg("删除成功");
		}

	}
	@RequestMapping("/toUserRole/{id}")
	public ModelAndView toUserRole(@PathVariable("id")String userId) {
		ModelAndView model = new ModelAndView();
		model.setViewName( "/system/user/userRoleList.html");
		model.addObject("userId", userId);
		return model;
	}

	/**
	 * 获取当前用户的角色Id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listCurrentUserRole/{id}")

	public List<Integer> listCurrentUserRole(@PathVariable("id")String userId){
		return userService.listUserRoleIdByUserId(userId);
	}

	/**
	 * 更新某个用户的role
	 * @param newRoleIdLst
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateRole/{id}")
	public Object updateRole(@PathVariable("id")String userId,@RequestParam("roleIds[]") List<Integer> newRoleIdLst) {
		try {
			userRoleService.updateUserRoleForUser(userId,newRoleIdLst);
		} catch (Exception e) {
			return ResponseResult.failedMsg("更新失败");
		}
		return  ResponseResult.successMsg("");
	}
}
