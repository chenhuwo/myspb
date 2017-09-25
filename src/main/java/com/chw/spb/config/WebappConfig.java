package com.chw.spb.config;

import com.chw.spb.interceptor.AjaxInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/**
* @Author chw
* @Date 2017/9/19
* @Description
*/
@Configuration
public class WebappConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       // registry.addInterceptor(ajaxInterceptor());
    }

    @Bean
    public AjaxInterceptor ajaxInterceptor() {
        return new AjaxInterceptor();
    }
}
