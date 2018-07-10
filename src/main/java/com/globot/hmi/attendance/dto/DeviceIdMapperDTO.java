package com.globot.hmi.attendance.dto;

/**
 * Created by Ambitous on 2018/1/9.
 */
public class DeviceIdMapperDTO {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DeviceIdMapperDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
