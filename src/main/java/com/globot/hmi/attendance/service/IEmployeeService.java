package com.globot.hmi.attendance.service;

import com.globot.hmi.attendance.domain.Employee;
import com.globot.hmi.attendance.dto.AddEmployeeDTO;
import com.globot.hmi.attendance.dto.UpdateEmployeeDTO;

import java.util.List;

/**
 * Created by Ambitous on 2017/11/29.
 */
public interface IEmployeeService {
    List<Employee> getAllEmployee();
    Integer addEmployee(AddEmployeeDTO addEmployeeDTO);
    Integer updateEmployee(UpdateEmployeeDTO updateEmployeeDTO);
    Employee getEmployeeByJobNumber(String jobNumber);
    Employee getEmployeeById(Integer id);
    Integer deleteEmployeeById(Integer id);
}
