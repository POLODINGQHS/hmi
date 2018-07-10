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
@RequestMapping("/exam")
public class ExamController {

    

    @RequestMapping("/newtest")
    @ResponseBody
    public WebResult userLogin(@RequestBody LoginDTO loginDTO){
        String info = "Test seccess!";
        return ResultUtil.success(info);
    }

    
}
