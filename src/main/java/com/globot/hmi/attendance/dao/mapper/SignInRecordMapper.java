package com.globot.hmi.attendance.dao.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.globot.hmi.attendance.domain.Leave;
import com.globot.hmi.attendance.domain.SignInRecord;
import com.globot.hmi.attendance.domain.SignInRecordExample;
import com.globot.hmi.attendance.dto.SignInRecordDTO;

public interface SignInRecordMapper {
    int countByExample(SignInRecordExample example);

    int deleteByExample(SignInRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SignInRecord record);

    int insertSelective(SignInRecord record);

    List<SignInRecord> selectByExample(SignInRecordExample example);

    SignInRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SignInRecord record, @Param("example") SignInRecordExample example);

    int updateByExample(@Param("record") SignInRecord record, @Param("example") SignInRecordExample example);

    int updateByPrimaryKeySelective(SignInRecord record);

    int updateByPrimaryKey(SignInRecord record);
    
    int countRealDays(@Param("jobNumber") String jobNumber,@Param("date") Date date);
    
    List<SignInRecordDTO> showSignInRecord();
    
    int applyLeave(Leave leave);
    
    List<Leave> getLeaveByJobNumber(String jobNumber);
    
    int countLeaveDays(@Param("jobNumber") String jobNumber,@Param("date") Date date);
    
    int countLateDays(@Param("jobNumber") String jobNumber,@Param("date") Date date);
    
    int countLeaveEarlyDays(@Param("jobNumber") String jobNumber,@Param("date") Date date);
}