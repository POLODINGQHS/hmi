package com.globot.hmi.attendance.dto;

/**
 * Created by Ambitous on 2018/1/9.
 */
public class EventIdMapperDTO {
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
        return "EventIdMapperDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
