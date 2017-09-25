package com.chw.spb.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.StringUtils;

import com.chw.spb.system.entity.User;
import com.chw.spb.system.service.UserService;
/**
* @Author chw
* @Date 2017/9/19
* @Description
*/
public class UserRealm extends AuthorizingRealm {
	
	private static final Logger log = LoggerFactory.getLogger(UserRealm.class);
	
	@Autowired
	@Lazy
	private UserService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		log.info("开始验证");
		
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();
		log.info("username->{},password->{}",username,new String(upToken.getPassword()));
		
		User user = userService.findUserByUsername(username);
		if(null == user){
			throw new UnknownAccountException();
		}
		
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), getName());
		
		return authenticationInfo;
	}
	
}
