package com.chw.spb.interceptor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
* @Author chw
* @Date 2017/9/19
* @Description
*/
public class AjaxInterceptor implements HandlerInterceptor{

    private static final Logger LOG = LoggerFactory.getLogger(AjaxInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ajaxSubmit = request.getHeader("X-Requested-With");
        if(ajaxSubmit != null && ajaxSubmit.equals("XMLHttpRequest") && !request.getRequestURI().contains("/login")){
            LOG.info("url-->{}",request.getRequestURL());
            HttpSession session = request.getSession(false);
            LOG.info("session :{}",session);
            if (request.getSession(false) == null) {
                response.setHeader("sessionstatus", "timeout");
                response.getWriter().print("sessionstatus");
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
