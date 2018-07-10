package com.globot.hmi.attendance.service;

import com.globot.hmi.attendance.dao.mapper.EmployeeMapper;
import com.globot.hmi.attendance.domain.Employee;
import com.globot.hmi.attendance.domain.EmployeeExample;
import com.globot.hmi.attendance.dto.AddEmployeeDTO;
import com.globot.hmi.attendance.dto.UpdateEmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Ambitous on 2017/11/29.
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public List<Employee> getAllEmployee() {
        return employeeMapper.selectByExample(new EmployeeExample());
    }

    @Override
    public Integer addEmployee(AddEmployeeDTO addEmployeeDTO) {
        Employee employee = convertDTO2Domain(addEmployeeDTO);
        System.out.println(employee.toString());
        return employeeMapper.insertSelective(employee);
    }

    @Override
    public Integer updateEmployee(UpdateEmployeeDTO updateEmployeeDTO) {
        Employee employee = convertDTO2Domain(updateEmployeeDTO);
        return employeeMapper.updateByPrimaryKeySelective(employee);
    }



    @Override
    public Employee getEmployeeByJobNumber(String jobNumber) {
        EmployeeExample example=new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andJobNumberEqualTo(jobNumber);
        Employee employee=employeeMapper.selectByJobNumber(jobNumber);
        return employee;
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        EmployeeExample example=new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        return employeeMapper.selectByExample(example).get(0);
    }

    @Override
    public Integer deleteEmployeeById(Integer id) {
        return employeeMapper.deleteByPrimaryKey(id);
    }


    private Employee convertDTO2Domain(AddEmployeeDTO addEmployeeDTO){
        Employee employee = new Employee();
        employee.setName(addEmployeeDTO.getName());
        if(addEmployeeDTO.getGender()==0)   employee.setGender(true);
        else employee.setGender(false);
        employee.setJobNumber(addEmployeeDTO.getJobNumber());
        employee.setEmail(addEmployeeDTO.getEmail());
        employee.setMobile(addEmployeeDTO.getPhone());
        employee.setPosition(addEmployeeDTO.getPosition());
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(addEmployeeDTO.getBirthday());
            employee.setBirthday(date);
        }catch (ParseException pe){
            pe.printStackTrace();
        }
        employee.setOrg(addEmployeeDTO.getOrg());
        employee.setMemo(addEmployeeDTO.getMes());
        return employee;
    }
    private Employee convertDTO2Domain(UpdateEmployeeDTO updateEmployeeDTO){
        Employee employee = new Employee();
        employee.setId(updateEmployeeDTO.getId());
        employee.setName(updateEmployeeDTO.getName());
        if(updateEmployeeDTO.getGender()==0)   employee.setGender(true);
        else employee.setGender(false);
        employee.setJobNumber(updateEmployeeDTO.getJobNumber());
        employee.setEmail(updateEmployeeDTO.getEmail());
        employee.setMobile(updateEmployeeDTO.getPhone());
        employee.setPosition(updateEmployeeDTO.getPosition());
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(updateEmployeeDTO.getBirthday());
            employee.setBirthday(date);
        }catch (ParseException pe){
            pe.printStackTrace();
        }
        employee.setOrg(updateEmployeeDTO.getOrg());
        employee.setMemo(updateEmployeeDTO.getMes());
        return employee;
    }
}
