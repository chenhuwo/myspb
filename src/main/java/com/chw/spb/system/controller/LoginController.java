package com.chw.spb.system.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chw.spb.common.ResponseResult;
/**
 * 
 * @author chw
 *
 */
@Controller
public class LoginController {
	
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public String tologin() {
		return "/login.html";
	}
	
	
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	@ResponseBody
	public Object login(@RequestParam("username")String username,String password) {
		if(StringUtils.isEmpty(username)) {
			return ResponseResult.failedMsg("用户名不能为空");
		}
		if(StringUtils.isEmpty(password)) {
			return ResponseResult.failedMsg("密码不能为空");
		}
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		
		Subject subject = SecurityUtils.getSubject();
		
		try {
			subject.login(token);
		} catch (UnknownAccountException e) {
			return ResponseResult.failedMsg("用户不存在");
		}catch (IncorrectCredentialsException e) {
			return ResponseResult.failedMsg("密码错误");
		}catch (AuthenticationException e) {
			return ResponseResult.failedMsg("用户名或者密码错误");
		}
		
		return ResponseResult.successMsg("登陆成功");
	}
	
	@RequestMapping("/logout")
	public String logout(){
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "/";
	}
	

	
}
