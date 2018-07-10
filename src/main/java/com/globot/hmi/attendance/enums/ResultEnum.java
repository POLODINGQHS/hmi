package com.globot.hmi.attendance.enums;

/**
 * Created by Ambitous on 2017/11/27.
 */
public enum ResultEnum {
    UNKONW_ERROR(-1, "未知错误"),
    SUCCESS(0, "成功"),
    USERNAME_OR_PASSWORD_ERROR(-1000,"用户名密码错误"),
    SESSION_OUTTIME(-2000,"此session已经过期，请重新登录"),
    GET_USER_BY_TOKEN_ERROR(-3000,"根据token获取Employee时发生异常")

    ;

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
