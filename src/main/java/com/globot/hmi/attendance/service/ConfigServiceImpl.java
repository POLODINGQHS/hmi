package com.globot.hmi.attendance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globot.hmi.attendance.dao.mapper.ConfigMapper;

@Service
public class ConfigServiceImpl implements IConfigService {
	
	@Autowired
	ConfigMapper configMapper;

	@Override
	public int readConfig() {
		return configMapper.readConfig();
	}

	@Override
	public int setConfig() {
		// TODO Auto-generated method stub
		return configMapper.setConfig();
	}

}
