package com.globot.hmi.attendance.web.interceptor;

import com.globot.hmi.attendance.domain.Employee;
import com.globot.hmi.attendance.service.ILoginService;
import com.globot.hmi.attendance.util.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Ambitous on 2017/11/28.
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private ILoginService loginService;

    @Value("${LOGIN_COOKIE}")
    private String LOGIN_COOKIE;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String token = CookieUtils.getCookieValue(httpServletRequest,"GLOBOT_TOKEN");
        System.out.println("token:"+token);
        Employee employee = loginService.getUserByToken(token);
        System.out.println("employee:"+employee);
        if(employee == null){
            //TODO 拦截成功，跳转到登录界面，并把用户请求的url作为参数
            httpServletResponse.sendRedirect("http://localhost:8080/hmi/portal/login.html");
            return false;
        }
        return true;//放行
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
