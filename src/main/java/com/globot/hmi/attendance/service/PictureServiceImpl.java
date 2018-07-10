package com.globot.hmi.attendance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globot.hmi.attendance.dao.mapper.PictureMapper;
import com.globot.hmi.attendance.domain.Picture;
import com.globot.hmi.attendance.domain.PictureExample;
import com.globot.hmi.attendance.domain.PictureExample.Criteria;

@Service
public class PictureServiceImpl implements IPitureService {

	@Autowired
	PictureMapper pictureMapper;

	@Override
	public List<Picture> getFacePicByJobNumber(String jobNumber) {
		PictureExample example=new PictureExample();
		Criteria criteria=example.createCriteria(); 
		criteria.andJobNumberEqualTo(jobNumber);
		criteria.andAvatarEqualTo((byte) 0);
		List<Picture> pictures=pictureMapper.selectByExample(example);
		return pictures;
	}

	@Override
	public Picture getAvatarByJobNumber(String jobNumber) {
		
		return null;
	}

	@Override
	public int insertFacePicRecord(Picture picture) {
		return pictureMapper.insert(picture);
	}

	@Override
	public Picture selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return pictureMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return pictureMapper.deleteByPrimaryKey(id);
	}
}
