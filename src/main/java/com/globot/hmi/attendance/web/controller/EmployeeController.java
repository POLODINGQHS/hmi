package com.globot.hmi.attendance.web.controller;

import com.globot.hmi.attendance.domain.Employee;
import com.globot.hmi.attendance.dto.AddEmployeeDTO;
import com.globot.hmi.attendance.dto.LoginDTO;
import com.globot.hmi.attendance.dto.UpdateEmployeeDTO;
import com.globot.hmi.attendance.enums.ResultEnum;
import com.globot.hmi.attendance.service.IEmployeeService;
import com.globot.hmi.attendance.util.result.ResultUtil;
import com.globot.hmi.attendance.util.result.WebResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ambitous on 2017/11/29.
 */
@Controller
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    IEmployeeService employeeService;

    @RequestMapping("/list")
    @ResponseBody
    public WebResult getAllEmployee(){
        List<Employee> result = new ArrayList<>();
        List<Employee> list = employeeService.getAllEmployee();
        for(int i=0;i<1;i++){
            result.addAll(list);
        }

        return ResultUtil.success(result);
    }

    @RequestMapping("/add")
    @ResponseBody
    public WebResult addEmployee(@RequestBody AddEmployeeDTO addEmployeeDTO){
        System.out.println("姓名:"+addEmployeeDTO.getName()+"  电话:"+addEmployeeDTO.getPhone());
        return ResultUtil.success(employeeService.addEmployee(addEmployeeDTO));
    }

    @RequestMapping("/update")
    @ResponseBody
    public WebResult updateEmployee(@RequestBody UpdateEmployeeDTO updateEmployeeDTO) {
        System.out.println("姓名:"+updateEmployeeDTO.getId()+"  电话:"+updateEmployeeDTO.getPhone());
        return ResultUtil.success(employeeService.updateEmployee(updateEmployeeDTO));
    }

    @RequestMapping("/getInfo/{jobNumber}")
    @ResponseBody
    public WebResult<Employee> getMenuStruc(@PathVariable String jobNumber) {
        // 理想情况，后续版本增加鲁棒性
        Employee employee = employeeService.getEmployeeByJobNumber(jobNumber);
        WebResult<Employee> webResut = new WebResult<>(ResultEnum.SUCCESS);
        webResut.setData(employee);
        return webResut;
    }

    @RequestMapping("/query/{id}")
    @ResponseBody
    public WebResult<Employee> getMesById(@PathVariable Integer id) {
        Employee employee = employeeService.getEmployeeById(id);
        return ResultUtil.success(employee);
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public WebResult<Employee> deleteById(@PathVariable Integer id) {
        return ResultUtil.success(employeeService.deleteEmployeeById(id));
    }




}
