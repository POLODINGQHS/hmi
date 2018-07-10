package com.globot.hmi.attendance.service;

import java.util.List;

import com.globot.hmi.attendance.vo.MenuVO;

public interface IMenuService {

	List<MenuVO> getParentMenuWithChild();
}
