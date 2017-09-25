package com.chw.spb.shiro.credentials;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
* @Author chw
* @Date 2017/9/19
* @Description
*/
public class CustomerCredentialsMatcher extends HashedCredentialsMatcher {
	
	private static final Logger log = LoggerFactory.getLogger(CustomerCredentialsMatcher.class);
	

	@Override
	protected boolean equals(Object tokenCredentials, Object accountCredentials) {
		log.info("用户输入口令加密：{},账号口令：{}",tokenCredentials.toString(),accountCredentials.toString());
		return super.equals(tokenCredentials, accountCredentials);
	}
	
	
}
