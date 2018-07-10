package com.globot.hmi.attendance.vo;

public class SignInInfoVO {
	private int totalDays;// 应到天数
	private int realDays;// 实到天数
	private float leaveDays;// 请假天数
	private float absentDays;// 旷工天数
	private int lateDays;// 迟到天数
	private int leaveEarlyDays;// 早退天数

	public int getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(int totalDays) {
		this.totalDays = totalDays;
	}

	public int getRealDays() {
		return realDays;
	}

	public void setRealDays(int realDays) {
		this.realDays = realDays;
	}

	public float getLeaveDays() {
		return leaveDays;
	}

	public void setLeaveDays(float leaveDays) {
		this.leaveDays = leaveDays;
	}

	public float getAbsentDays() {
		return absentDays;
	}

	public void setAbsentDays(float absentDays) {
		this.absentDays = absentDays;
	}

	public int getLateDays() {
		return lateDays;
	}

	public void setLateDays(int lateDays) {
		this.lateDays = lateDays;
	}

	public int getLeaveEarlyDays() {
		return leaveEarlyDays;
	}

	public void setLeaveEarlyDays(int leaveEarlyDays) {
		this.leaveEarlyDays = leaveEarlyDays;
	}

}
