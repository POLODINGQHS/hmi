package com.globot.hmi.attendance.domain;

import java.util.Date;

public class EmployeeRole {
    private Integer id;

    private Integer employeeId;

    private Integer roleId;

    private Date addTime;

    private Date modifyTime;

    private String mome;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
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