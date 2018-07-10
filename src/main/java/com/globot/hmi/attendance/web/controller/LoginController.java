package com.globot.hmi.attendance.web.controller;

import com.globot.hmi.attendance.domain.Employee;
import com.globot.hmi.attendance.dto.LoginDTO;
import com.globot.hmi.attendance.enums.ResultEnum;
import com.globot.hmi.attendance.redis.JedisClient;
import com.globot.hmi.attendance.service.ILoginService;
import com.globot.hmi.attendance.util.CookieUtils;
import com.globot.hmi.attendance.util.result.ResultUtil;
import com.globot.hmi.attendance.util.result.WebResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Ambitous on 2017/11/27.
 */
@Controller
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    ILoginService loginService;

    @Autowired
    JedisClient jedisClient;

    @Value("${REDIS_USER_SESSION_KEY}")
    private String REDIS_USER_SESSION_KEY;

    @RequestMapping("/login")
    @ResponseBody
    public WebResult userLogin(@RequestBody LoginDTO loginDTO){
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        System.out.println("username:"+loginDTO.getUsername()+" "+"password:"+loginDTO.getPassword());
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
        String token = loginService.userLogin(username,password,request,response);
        if(token==null)
            return ResultUtil.error(ResultEnum.USERNAME_OR_PASSWORD_ERROR);
        return ResultUtil.success(token);
    }

    @RequestMapping("/token/{token}")
    @ResponseBody
    public WebResult getUserByToken(@PathVariable String token){
        Employee employee = null;
        try{
            employee = loginService.getUserByToken(token);
        } catch (Exception e){
            e.printStackTrace();
        }
        if(employee==null)
            return ResultUtil.error(ResultEnum.GET_USER_BY_TOKEN_ERROR);
        return ResultUtil.success(employee);
    }

    @RequestMapping("/logout")
    @ResponseBody
    public WebResult logout(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
        String token = CookieUtils.getCookieValue(request,"GLOBOT_TOKEN");
        CookieUtils.deleteCookie(request,response,"GLOBOT_TOKEN");
        jedisClient.del(REDIS_USER_SESSION_KEY+":"+token);
        return ResultUtil.success();
    }

}
