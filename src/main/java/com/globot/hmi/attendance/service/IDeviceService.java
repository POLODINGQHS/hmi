package com.globot.hmi.attendance.service;

import com.globot.hmi.attendance.domain.Device;
import com.globot.hmi.attendance.dto.DeviceIdMapperDTO;

import java.util.List;

/**
 * Created by Ambitous on 2017/11/29.
 */
public interface IDeviceService {

    List<Device> getAllDevice();
    Integer addDevice(Device device);
    Device getDeviceById(Integer id);
    Integer updateDevice(Device device);
    Integer deleteDeviceById(Integer id);
    List<DeviceIdMapperDTO> getDeviceIdMapper();
}
