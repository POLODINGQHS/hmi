package com.globot.hmi.attendance.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globot.hmi.attendance.dao.mapper.SignInRecordMapper;
import com.globot.hmi.attendance.domain.Leave;
import com.globot.hmi.attendance.domain.SignInRecord;
import com.globot.hmi.attendance.domain.SignInRecordExample;
import com.globot.hmi.attendance.domain.SignInRecordExample.Criteria;
import com.globot.hmi.attendance.dto.SignInRecordDTO;
import com.globot.hmi.attendance.util.DateUtils;
import com.globot.hmi.attendance.vo.SignInInfoVO;;

@Service
public class SignInRecordServiceImpl implements ISignInRecordService {

	@Autowired
	SignInRecordMapper signInRecordMapper;

	@Override
	public List<SignInRecord> getRecordByJobNumber(String jobNumber) {
		SignInRecordExample example=new SignInRecordExample();
		Criteria criteria = example.createCriteria();
		criteria.andJobNumberEqualTo(jobNumber);
		List<SignInRecord> records=signInRecordMapper.selectByExample(example);
		return records;
	}

	@Override
	public SignInInfoVO getSignInInfo(String jobNumber,String timestamp) {
		SignInInfoVO signInInfo = new SignInInfoVO();
		Date now = new Date();
		Date date = DateUtils.getDateByTimeStamp(timestamp);
		int totalDays = DateUtils.getTotalDays(date);
		int tmp_days = 0;
		if(date.getYear()<now.getYear() || (date.getYear() == now.getYear() && date.getMonth()<now.getMonth())){
			tmp_days = totalDays;
		}else if(date.getYear() == now.getYear() && date.getMonth()==now.getMonth()){
			tmp_days = DateUtils.getInsMonthTotaldays(date);
		}else{
			return signInInfo;
		}
		
		int realDays = signInRecordMapper.countRealDays(jobNumber, date);
		float leaveDays = Float.valueOf(signInRecordMapper.countLeaveDays(jobNumber, date))/2;
		int earlyLeaveDays = signInRecordMapper.countLeaveEarlyDays(jobNumber, date);
		int lateDays = signInRecordMapper.countLateDays(jobNumber, date);
		float absentDays = tmp_days - realDays-leaveDays;
		signInInfo.setTotalDays(totalDays);
		signInInfo.setRealDays(realDays);
		signInInfo.setLeaveDays(leaveDays);
		signInInfo.setLateDays(lateDays);
		signInInfo.setLeaveEarlyDays(earlyLeaveDays);
		signInInfo.setAbsentDays(absentDays);
		return signInInfo;
	}

	@Override
	public List<SignInRecordDTO> showSignInRecord() {
		return signInRecordMapper.showSignInRecord();
	}

	@Override
	public int applyLeave(Leave leave) {
		System.out.println(leave.getReason());
		return signInRecordMapper.applyLeave(leave);
	}

	@Override
	public List<Leave> getLeaveByJobNumber(String jobNumber) {
		return signInRecordMapper.getLeaveByJobNumber(jobNumber);
	}
}
