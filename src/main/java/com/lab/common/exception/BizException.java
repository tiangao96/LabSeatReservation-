package com.lab.common.exception;

import com.lab.common.api.ResultCode;

public class BizException
extends RuntimeException {
    private final ResultCode code;

    public BizException(ResultCode code) {
        super(code.getMessage());
        this.code = code;
    }

    public BizException(ResultCode code, String message) {
        super(message);
        this.code = code;
    }

    public ResultCode getCode() {
        return this.code;
    }
}

