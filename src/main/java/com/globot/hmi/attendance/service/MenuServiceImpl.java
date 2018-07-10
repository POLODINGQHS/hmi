package com.globot.hmi.attendance.service;

import java.util.ArrayList;
import java.util.List;

import com.globot.hmi.attendance.domain.Menu;
import org.springframework.beans.factory.annotation.Autowired;

import com.globot.hmi.attendance.dao.mapper.MenuMapper;
import com.globot.hmi.attendance.vo.MenuVO;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements IMenuService {

	@Autowired
	MenuMapper menuMapper;
	
	@Override
	public List<MenuVO> getParentMenuWithChild() {
//		MenuExample menuExample = new MenuExample();
//		MenuExample.Criteria criteria = menuExample.createCriteria();
		List<Menu> parentMenus=menuMapper.selectParentMenu();
		List<MenuVO> parentMenuWithChild=new ArrayList<>();
		for(Menu parentMenu:parentMenus){
			MenuVO menuVO=new MenuVO(parentMenu);
			int menuId=menuVO.getId();
//			criteria.andParentIdEqualTo(menuId);
//			menuVO.setChildMenu(menuMapper.selectByExample(menuExample));
			menuVO.setChildMenu(menuMapper.selectChildMenuByParentId(menuId));
			parentMenuWithChild.add(menuVO);
		}
		return parentMenuWithChild;
	}

}
