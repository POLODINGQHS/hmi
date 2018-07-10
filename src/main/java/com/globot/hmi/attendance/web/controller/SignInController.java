package com.globot.hmi.attendance.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.globot.hmi.attendance.domain.Employee;
import com.globot.hmi.attendance.domain.Leave;
import com.globot.hmi.attendance.domain.SignInRecord;
import com.globot.hmi.attendance.enums.ResultEnum;
import com.globot.hmi.attendance.service.ILoginService;
import com.globot.hmi.attendance.service.ISignInRecordService;
import com.globot.hmi.attendance.util.result.ResultUtil;
import com.globot.hmi.attendance.util.result.WebResult;
import com.globot.hmi.attendance.vo.SignInInfoVO;

@Controller
@RequestMapping("/api/signIn")
public class SignInController {

	@Autowired
	ILoginService loginService;

	@Autowired
	ISignInRecordService signInRecordService;

	@RequestMapping("/record/{token}")
	@ResponseBody
	public WebResult<Map<String,Object>> getSignInRecord(@PathVariable String token) {
		// 理想情况，后续版本增加鲁棒性
		//包括请假记录
		Employee employee = loginService.getUserByToken(token);
		List<SignInRecord> records = signInRecordService.getRecordByJobNumber(employee.getJobNumber());
		for(int i=0;i<records.size();i++) {
			String signInPic = records.get(i).getSignInPic();
			records.get(i).setSignInPic("/hmi/signin/"+employee.getJobNumber()+"/"+signInPic);
		}
		List<Leave> leaves = signInRecordService.getLeaveByJobNumber(employee.getJobNumber());
		SignInInfoVO signInInfo = signInRecordService.getSignInInfo(employee.getJobNumber(),null);
		Map<String,Object> data=new HashMap<>();
		data.put("records", records);
		data.put("signInInfo", signInInfo);
		data.put("leaves", leaves);
		WebResult<Map<String,Object>> webResut = new WebResult<>(ResultEnum.SUCCESS);
		webResut.setData(data);
		return webResut;
	}

	@RequestMapping("/info/{token}")
	@ResponseBody
	public WebResult<SignInInfoVO> getSignInInfo(@PathVariable String token,String date) {
		System.out.println(date);
		// 理想情况，后续版本增加鲁棒性
		Employee employee = loginService.getUserByToken(token);
		SignInInfoVO signInInfo = signInRecordService.getSignInInfo(employee.getJobNumber(),date);
		WebResult<SignInInfoVO> webResut = new WebResult<>(ResultEnum.SUCCESS);
		webResut.setData(signInInfo);
		return webResut;
	}
	
	@RequestMapping("/record/all")
	@ResponseBody
	public WebResult showSignInRecord(){
		return ResultUtil.success(signInRecordService.showSignInRecord());
	}
	
	@RequestMapping("/record/applyleave/{token}")
	@ResponseBody
	public WebResult<Map<String, Object>> applyLeave(@PathVariable String token,@RequestBody Leave leave) {
		// 理想情况，后续版本增加鲁棒性
		Employee employee = loginService.getUserByToken(token);
		leave.setJobNumber(employee.getJobNumber());
		int returnToken = signInRecordService.applyLeave(leave);
		WebResult<Map<String,Object>> webResut = new WebResult<>(ResultEnum.SUCCESS);
		webResut.setCode(returnToken);
		return webResut;
	}
}
