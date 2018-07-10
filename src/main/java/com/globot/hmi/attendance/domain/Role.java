package com.globot.hmi.attendance.domain;

import java.util.Date;

public class Role {
    private Integer id;

    private String name;

    private Date addTime;

    private Date modifyTime;

    private Byte status;

    private String mome;

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
        this.name = name == null ? null : name.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getMome() {
        return mome;
    }

    public void setMome(String mome) {
        this.mome = mome == null ? null : mome.trim();
    }
}