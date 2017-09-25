package com.chw.spb.shiro.filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author chw
 * 2017/9/20
 */
public class CustomerFormAuthFilter extends FormAuthenticationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isAjax(request) && !isLoginRequest(request,response)) {
            Subject subject = SecurityUtils.getSubject();
            if (!subject.isAuthenticated()) {
                System.out.println(getPathWithinApplication(request));
                HttpServletResponse res = (HttpServletResponse)response;
                res.setHeader("sessionstatus", "timeout");
                return false;
            }
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }

    private boolean isAjax(ServletRequest request) {
        HttpServletRequest req = (HttpServletRequest) request;
        String ajaxSubmit = req.getHeader("X-Requested-With");
        if (ajaxSubmit != null && ajaxSubmit.equals("XMLHttpRequest")) {
            return true;
        } else {
            return false;
        }
    }

}
