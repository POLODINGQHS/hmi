package com.globot.hmi.attendance.service;

import java.util.List;

import com.globot.hmi.attendance.domain.Log;

public interface ILogService {
	List<Log> getLog();
	int addLog(Log log);
}
