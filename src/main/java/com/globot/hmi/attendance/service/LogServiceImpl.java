package com.globot.hmi.attendance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globot.hmi.attendance.dao.mapper.LogMapper;
import com.globot.hmi.attendance.domain.Log;

@Service
public class LogServiceImpl implements ILogService {
	
	@Autowired
	LogMapper logMapper;

	@Override
	public List<Log> getLog() {
		return logMapper.getLog();
	}

	@Override
	public int addLog(Log log) {
		return logMapper.addLog(log);
	}

}
