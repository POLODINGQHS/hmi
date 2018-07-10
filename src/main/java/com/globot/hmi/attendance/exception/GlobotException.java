package com.globot.hmi.attendance.exception;

import com.globot.hmi.attendance.enums.ResultEnum;

/**
 * Created by Ambitous on 2017/11/27.
 */
public class GlobotException extends RuntimeException {

    private Integer code;

    public GlobotException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
