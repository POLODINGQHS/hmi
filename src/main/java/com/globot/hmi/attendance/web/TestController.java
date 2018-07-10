package com.globot.hmi.attendance.web;

import com.globot.hmi.attendance.dao.mapper.EmployeeMapper;
import com.globot.hmi.attendance.domain.Employee;
import com.globot.hmi.attendance.redis.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ambitous on 2017/11/27.
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    JedisClient jedisClient;

    @RequestMapping("/api")
    @ResponseBody
    public Employee hello(){
        Employee employee = employeeMapper.selectByPrimaryKey(10000);
//        jedisClient.set("wangjian","666");
//        String str = jedisClient.get("wangjian");
        return employee;
    }
}
