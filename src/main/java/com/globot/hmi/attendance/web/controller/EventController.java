package com.globot.hmi.attendance.web.controller;

import com.globot.hmi.attendance.domain.Event;
import com.globot.hmi.attendance.service.IEventService;
import com.globot.hmi.attendance.util.result.ResultUtil;
import com.globot.hmi.attendance.util.result.WebResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Ambitous on 2017/11/29.
 */
@Controller
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    IEventService eventService;

    @RequestMapping("/list")
    @ResponseBody
    public WebResult getAllEvent(){
        return ResultUtil.success(eventService.getAllEvent());
    }

    @RequestMapping("/add")
    @ResponseBody
    public WebResult addEvent(@RequestBody Event event){
        System.out.println(event.toString());
        return ResultUtil.success(eventService.addEvent(event));
    }

    @RequestMapping("/query/{id}")
    @ResponseBody
    public WebResult getEventById(@PathVariable Integer id){
        return ResultUtil.success(eventService.getEventById(id));
    }

    @RequestMapping("/update")
    @ResponseBody
    public WebResult updateEvent(@RequestBody Event event){
        return ResultUtil.success(eventService.updateEvent(event));
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public WebResult deleteEventById(@PathVariable Integer id){
        return ResultUtil.success(eventService.deleteEventById(id));
    }

    @RequestMapping("/mapper")
    @ResponseBody
    public WebResult getEventIdMapper(){
        return ResultUtil.success(eventService.getEventIdMapper());
    }
}
