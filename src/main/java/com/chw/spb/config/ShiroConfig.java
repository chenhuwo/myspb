package com.chw.spb.config;

import java.util.HashMap;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;

import com.chw.spb.shiro.filter.CustomerFormAuthFilter;
import com.google.common.collect.Maps;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import com.chw.spb.shiro.credentials.CustomerCredentialsMatcher;
import com.chw.spb.shiro.realm.UserRealm;
/**
 * 
 * @author chw
 *
 */
@Configuration
public class ShiroConfig {
	
	
	
	
	/**
	 * 注册setvlet
	 * @Bean
	 * @return
	 */
	public ServletRegistrationBean servletBean(){
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new DispatcherServlet(),"/");
		//servletRegistrationBean.setServlet(new DispatcherServlet());
		return servletRegistrationBean;
	}
	/**
	 * filter
	 * @return
	 */
	
	
	@Bean
	public FilterRegistrationBean shiroFilterRegistration(){
		FilterRegistrationBean filterRegistBean = new FilterRegistrationBean();
		filterRegistBean.setFilter(new DelegatingFilterProxy("shiroFilter"));
		filterRegistBean.addUrlPatterns("/*");
		filterRegistBean.setDispatcherTypes(DispatcherType.REQUEST);
		return filterRegistBean;
	}
	
	
	
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		//注册filter 有默认的filter
		//shiroFilterFactoryBean.setFilters(null);
		//登陆url
		shiroFilterFactoryBean.setLoginUrl("/login");
		//shiroFilterFactoryBean.setSuccessUrl("");
		//shiroFilterFactoryBean.setUnauthorizedUrl("");
		//请求过滤链
		HashMap<String, Filter> filterMap = Maps.newHashMap();
		filterMap.put("authc",new CustomerFormAuthFilter());
		shiroFilterFactoryBean.setFilters(filterMap);
		HashMap<String, String> chain = new HashMap<>();
		chain.put("/login", "anon");
		chain.put("/logout", "logout");
		chain.put("/css/**", "anon");
		chain.put("/js/**", "anon");
		chain.put("/image/**", "anon");
		chain.put("/assets/**", "anon");
		chain.put("/**", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(chain);
		return shiroFilterFactoryBean;
	}
	
	@Bean
	public DefaultWebSecurityManager securityManager(DefaultWebSessionManager sessionManager ){
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		//realm
		manager.setRealm(userRealm());
		//cache
		//session
		manager.setSessionManager(sessionManager);
		return manager;
	}
	
	@Bean
	public DefaultWebSessionManager sessionManager(){
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		
		//sessionManager.setSessionIdCookie(sessionIdCookie);
		sessionManager.setGlobalSessionTimeout(1800000);//30min
		sessionManager.setSessionIdUrlRewritingEnabled(false);
		
		/*
		 *是否启用/禁用 Session Id Cookie，默认是启用的；
			如果禁用后将不会设置 Session Id Cookie，即默认使用了 Servlet 容器的 JSESSIONID，且通
			过 URL 重写（URL 中的“;JSESSIONID=id”部分）保存 Session Id 
		 */
		
		//默认开启
		//sessionManager.setSessionIdCookieEnabled(false);
		//sessionManager.setDeleteInvalidSessions(true);
		//会话调度验证默认开启
		//sessionManager.setSessionValidationSchedulerEnabled(sessionValidationSchedulerEnabled);
		//会话调度间隔时间 默认
		//sessionManager.setSessionValidationInterval(sessionValidationInterval);
		return sessionManager;
	}
	
	
	@Bean
	public UserRealm userRealm(){
		UserRealm realm = new UserRealm();
		realm.setCredentialsMatcher(credentialsMatcher());
		return realm;
	}
	
	@Bean
	public CustomerCredentialsMatcher credentialsMatcher(){
		CustomerCredentialsMatcher credentialsMatcher = new CustomerCredentialsMatcher();
		credentialsMatcher.setHashAlgorithmName("MD5");//加密方式
		return credentialsMatcher;
	}
	
	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}
	
}
