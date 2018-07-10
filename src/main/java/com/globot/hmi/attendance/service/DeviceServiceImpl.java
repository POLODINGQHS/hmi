package com.globot.hmi.attendance.service;

import com.globot.hmi.attendance.dao.mapper.DeviceMapper;
import com.globot.hmi.attendance.domain.Device;
import com.globot.hmi.attendance.domain.DeviceExample;
import com.globot.hmi.attendance.dto.DeviceIdMapperDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Ambitous on 2017/11/29.
 */
@Service
public class DeviceServiceImpl implements IDeviceService{

    @Autowired
    DeviceMapper deviceMapper;

    @Override
    public List<Device> getAllDevice() {
        DeviceExample deviceExample = new DeviceExample();
        DeviceExample.Criteria criteria = deviceExample.createCriteria();
        return deviceMapper.selectByExample(deviceExample);
    }

    @Override
    public Integer addDevice(Device device) {
        device.setAddTime(new Date());
        device.setModifyTime(new Date());
        return deviceMapper.insertSelective(device);
    }

    @Override
    public Device getDeviceById(Integer id) {
        DeviceExample deviceExample = new DeviceExample();
        DeviceExample.Criteria criteria = deviceExample.createCriteria();
        criteria.andIdEqualTo(id);
        return deviceMapper.selectByExample(deviceExample).get(0);
    }

    @Override
    public Integer updateDevice(Device device) {
        return deviceMapper.updateByPrimaryKeySelective(device);
    }

    @Override
    public Integer deleteDeviceById(Integer id) {
        return deviceMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<DeviceIdMapperDTO> getDeviceIdMapper() {
        return deviceMapper.getDeviceIdMapper();
    }


}
