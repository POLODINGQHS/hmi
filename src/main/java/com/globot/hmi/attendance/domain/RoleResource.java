package com.globot.hmi.attendance.domain;

import java.util.Date;

public class RoleResource {
    private Integer id;

    private Integer roleId;

    private Integer resourceId;

    private Date addTime;

    private Date modifyTime;

    private String mome;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
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

    public String getMome() {
        return mome;
    }

    public void setMome(String mome) {
        this.mome = mome == null ? null : mome.trim();
    }
}