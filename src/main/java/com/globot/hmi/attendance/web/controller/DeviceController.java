package com.globot.hmi.attendance.web.controller;

import com.globot.hmi.attendance.domain.Device;
import com.globot.hmi.attendance.service.IDeviceService;
import com.globot.hmi.attendance.util.result.ResultUtil;
import com.globot.hmi.attendance.util.result.WebResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ambitous on 2017/11/29.
 */
@Controller
@RequestMapping("/api/device")
public class DeviceController {

    @Autowired
    IDeviceService deviceService;

    @RequestMapping("/list")
    @ResponseBody
    public WebResult getAllDevice(){
        List<Device> result = new ArrayList<>();
        List<Device> list = deviceService.getAllDevice();
        result.addAll(list);
        return ResultUtil.success(result);
    }

    @RequestMapping("/add")
    @ResponseBody
    public WebResult addDevice(@RequestBody Device device){
        System.out.println(device.toString());
        return ResultUtil.success(deviceService.addDevice(device));
    }

    @RequestMapping("/query/{id}")
    @ResponseBody
    public WebResult getDeviveById(@PathVariable Integer id){
        return ResultUtil.success(deviceService.getDeviceById(id));
    }

    @RequestMapping("/update")
    @ResponseBody
    public WebResult updateDevice(@RequestBody Device device){
        return ResultUtil.success(deviceService.updateDevice(device));
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public WebResult deleteDeviceById(@PathVariable Integer id){
        return ResultUtil.success(deviceService.deleteDeviceById(id));
    }

    @RequestMapping("/mapper")
    @ResponseBody
    public WebResult getDeviceIdMapper(){
        return ResultUtil.success(deviceService.getDeviceIdMapper());
    }


}
