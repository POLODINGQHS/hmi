package com.globot.hmi.attendance.service;

import com.globot.hmi.attendance.domain.Employee;
import com.globot.hmi.attendance.util.result.WebResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Ambitous on 2017/11/27.
 */
public interface ILoginService {
    String userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response);
    Employee getUserByToken(String token);
}
