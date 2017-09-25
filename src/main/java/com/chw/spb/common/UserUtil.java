package com.chw.spb.common;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import com.chw.spb.system.entity.User;




public class UserUtil {
	/**
	 * 获取当前用户对象shiro
	 * @return 
	 */
	public static User getCurrentShiroUser(){
		User user=(User) SecurityUtils.getSubject().getPrincipal();
		return user;
	}
	
	/**
	 * 获取当前用户session
	 * @return session
	 */
	public static Session getSession(){
		Session session =SecurityUtils.getSubject().getSession();
		return session;
	}
	
	/**
	 * 获取当前用户httpsession
	 * @return httpsession
	 */
	public static Session getHttpSession(){
		Session session =SecurityUtils.getSubject().getSession();
		return session;
	}
	
	/**
	 * 获取当前用户对象
	 * @return user
	 */
	public static User getCurrentUser(){
		Session session =SecurityUtils.getSubject().getSession();
		if(null!=session){
			return (User) session.getAttribute("user");
		}else{
			return null;
		}
		
	}
}
 