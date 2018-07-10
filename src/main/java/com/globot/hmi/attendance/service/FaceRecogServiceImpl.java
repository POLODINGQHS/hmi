package com.globot.hmi.attendance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globot.hmi.attendance.dao.mapper.FaceRecogMapper;
import com.globot.hmi.attendance.domain.FaceRecog;

@Service
public class FaceRecogServiceImpl implements IFaceRecogService {
	@Autowired
	FaceRecogMapper faceRecogMapper;
	@Override
	public Integer insertFaceRecog(FaceRecog faceRecog) {
		// TODO Auto-generated method stub
		return faceRecogMapper.insertFaceRecog(faceRecog);
	}

}
