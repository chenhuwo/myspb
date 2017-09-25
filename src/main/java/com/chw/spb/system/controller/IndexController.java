package com.chw.spb.system.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.chw.spb.system.entity.User;
import com.chw.spb.system.service.PermissionService;
/**
 * 
 * @author chw
 *
 */
@Controller
public class IndexController {
	
	private static final Logger log = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	private PermissionService permissionService;
	
	@RequestMapping({"/","/index"})
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/index.html");
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		log.info("当前用户：{}",user.toString());
		mav.addObject("permission", permissionService.getCurrentUserPermission(user.getId()+""));
		return mav;
	}
	
}
