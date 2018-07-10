package com.globot.hmi.attendance.domain;

import java.util.Date;

public class SignInRecord {
    private Integer id;

    private String jobNumber;

    private Date signInTime;

    private String signInPic;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber == null ? null : jobNumber.trim();
    }

    public Date getSignInTime() {
        return signInTime;
    }

    public void setSignInTime(Date signInTime) {
        this.signInTime = signInTime;
    }

    public String getSignInPic() {
        return signInPic;
    }

    public void setSignInPic(String signInPic) {
        this.signInPic = signInPic == null ? null : signInPic.trim();
    }
}