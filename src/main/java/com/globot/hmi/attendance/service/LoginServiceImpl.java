package com.globot.hmi.attendance.service;

import com.alibaba.fastjson.JSON;
import com.globot.hmi.attendance.dao.mapper.EmployeeMapper;
import com.globot.hmi.attendance.domain.Employee;
import com.globot.hmi.attendance.domain.EmployeeExample;
import com.globot.hmi.attendance.domain.EmployeeExample.Criteria;
import com.globot.hmi.attendance.enums.ResultEnum;
import com.globot.hmi.attendance.redis.JedisClient;
import com.globot.hmi.attendance.util.CookieUtils;
import com.globot.hmi.attendance.util.result.ResultUtil;
import com.globot.hmi.attendance.util.result.WebResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

/**
 * Created by Ambitous on 2017/11/27.
 */
@Service
public class LoginServiceImpl implements ILoginService{

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    JedisClient jedisClient;

    @Value("${LOGIN_COOKIE}")
    private String LOGIN_COOKIE;

    @Value("${REDIS_USER_SESSION_KEY}")
    private String REDIS_USER_SESSION_KEY;

    @Override
    public String userLogin(String username, String password,
                               HttpServletRequest request, HttpServletResponse response) {
        EmployeeExample employeeExample = new EmployeeExample();
        Criteria criteria = employeeExample.createCriteria();
        criteria.andJobNumberEqualTo(username);
        List<Employee> list = employeeMapper.selectByExample(employeeExample);
        if(list==null || list.size()==0)
            return null;
        Employee employee = list.get(0);
        if(!DigestUtils.md5DigestAsHex(password.getBytes()).equals(employee.getPassword()))
            return null;

        String token = UUID.randomUUID().toString();
        employee.setPassword(null);
        jedisClient.set(REDIS_USER_SESSION_KEY+":"+token, JSON.toJSONString(employee));
        jedisClient.expire(REDIS_USER_SESSION_KEY+":"+token, 1800);
        CookieUtils.setCookie(request,response,"GLOBOT_TOKEN",token);

        return token;
    }

    @Override
    public Employee getUserByToken(String token) {
        String json = jedisClient.get(REDIS_USER_SESSION_KEY+":"+token);
        if(StringUtils.isEmpty(json))
            return null;
        jedisClient.expire(REDIS_USER_SESSION_KEY+":"+token, 1800);
        return JSON.parseObject(json,Employee.class);
    }
}
