package com.globot.hmi.attendance.service;

import java.util.List;

import com.globot.hmi.attendance.domain.Leave;
import com.globot.hmi.attendance.domain.SignInRecord;
import com.globot.hmi.attendance.dto.SignInRecordDTO;
import com.globot.hmi.attendance.vo.SignInInfoVO;

public interface ISignInRecordService {
	List<SignInRecord> getRecordByJobNumber(String jobNumber);

	SignInInfoVO getSignInInfo(String jobNumber, String date);
	
	List<SignInRecordDTO> showSignInRecord();
	
	int applyLeave(Leave leave);
	
	List<Leave> getLeaveByJobNumber(String jobNumber);
}
