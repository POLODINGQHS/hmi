package com.globot.hmi.attendance.service;

import java.util.List;

import com.globot.hmi.attendance.domain.Picture;

public interface IPitureService {
	List<Picture> getFacePicByJobNumber(String jobNumber);
	
	Picture getAvatarByJobNumber(String jobNumber);
	
	int insertFacePicRecord(Picture picture);
	
	Picture selectByPrimaryKey(Integer id);
	
	int deleteByPrimaryKey(Integer id);
}
