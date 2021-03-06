package com.globot.hmi.attendance.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/anno")
public class DemoAnnoController {	
	@RequestMapping(produces="text/plain;charset=UTF-8")
	public @ResponseBody String index(HttpServletRequest request){
		return "url:"+request.getRequestURL()+"can access";
	}	
	@RequestMapping(value="/pathvar/{str}",produces="text/plain;charset=UTF-8")
	public @ResponseBody String demoPathVar(@PathVariable String str,HttpServletRequest request){
		return "url:"+request.getRequestURL()+"can access,str:"+str;
	}	
	@RequestMapping(value="/requestParam",produces="text/plain;charset=UTF-8")
	//访问路径为：/anno/requestParam?id=1
	public @ResponseBody String passRequestParam(Long id,HttpServletRequest request){
		return "url:"+request.getRequestURL()+"can access,id:"+id;
	}
	@RequestMapping(value="/objRequestBody",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String passObjByRequestBody(@RequestBody DemoObj obj,HttpServletRequest request){
		return "url:"+request.getRequestURL()+"can access,obj id:"+obj.getId()+"obj name:"+obj.getName();
	}
	@RequestMapping(value="/obj",produces="application/json;charset=UTF-8")
	@ResponseBody
	//访问路径为：/anno/obj?id=1&name=xx
	public DemoObj passObj(DemoObj obj,HttpServletRequest request){
//		return "url:"+request.getRequestURL()+"can access,obj id:"+obj.getId()+"obj name:"+obj.getName();
		return obj;
	}	
	@RequestMapping(value={"/name1","/name2"},produces="text/plain;charset=UTF-8")
	public @ResponseBody String remove(HttpServletRequest request){
		return "url:"+request.getRequestURL()+"can access";
	}
}
