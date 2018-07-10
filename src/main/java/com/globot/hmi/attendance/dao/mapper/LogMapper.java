package com.globot.hmi.attendance.dao.mapper;

import java.util.List;

import com.globot.hmi.attendance.domain.Log;

public interface LogMapper {
	
	List<Log> getLog();
	int addLog(Log log);
}
