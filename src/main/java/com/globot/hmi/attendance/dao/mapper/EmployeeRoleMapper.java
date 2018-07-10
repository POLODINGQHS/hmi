package com.globot.hmi.attendance.dao.mapper;

import com.globot.hmi.attendance.domain.EmployeeRole;
import com.globot.hmi.attendance.domain.EmployeeRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmployeeRoleMapper {
    int countByExample(EmployeeRoleExample example);

    int deleteByExample(EmployeeRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EmployeeRole record);

    int insertSelective(EmployeeRole record);

    List<EmployeeRole> selectByExample(EmployeeRoleExample example);

    EmployeeRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EmployeeRole record, @Param("example") EmployeeRoleExample example);

    int updateByExample(@Param("record") EmployeeRole record, @Param("example") EmployeeRoleExample example);

    int updateByPrimaryKeySelective(EmployeeRole record);

    int updateByPrimaryKey(EmployeeRole record);
}