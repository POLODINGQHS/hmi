package com.globot.hmi.attendance.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.globot.hmi.attendance.enums.ResultEnum;
import com.globot.hmi.attendance.service.IMenuService;
import com.globot.hmi.attendance.util.result.WebResult;
import com.globot.hmi.attendance.vo.MenuVO;

@Controller
@RequestMapping("/api/menu")
public class MenuController {

	@Autowired
	IMenuService menuService;
	
	@RequestMapping("/struc")
    @ResponseBody
	public WebResult<List<MenuVO>> getMenuStruc(){
		List<MenuVO> parentMenuWithChild=menuService.getParentMenuWithChild();
		WebResult<List<MenuVO>> webResut=new WebResult<List<MenuVO>>(ResultEnum.SUCCESS);
		webResut.setData(parentMenuWithChild);
		return webResut;
	}
}
