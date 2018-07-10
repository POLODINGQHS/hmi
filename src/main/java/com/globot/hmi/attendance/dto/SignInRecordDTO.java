package com.globot.hmi.attendance.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by Ambitous on 2018/1/18.
 */
public class SignInRecordDTO {
    private String name;
    private String jobNumber;
    private String org;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date day;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="HH:mm:ss",timezone = "GMT+8")
    private Date uptime;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="HH:mm:ss",timezone = "GMT+8")
    private Date downtime;
    private Integer state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public Date getUptime() {
        return uptime;
    }

    public void setUptime(Date uptime) {
        this.uptime = uptime;
    }

    public Date getDowntime() {
        return downtime;
    }

    public void setDowntime(Date downtime) {
        this.downtime = downtime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "SignInRecordDTO{" +
                "name='" + name + '\'' +
                ", jobNumber='" + jobNumber + '\'' +
                ", org='" + org + '\'' +
                ", day=" + day +
                ", uptime=" + uptime +
                ", downtime=" + downtime +
                ", state=" + state +
                '}';
    }
}
