package com.lab.common.api;


public enum ResultCode {
    SUCCESS(0, "success"),
    BAD_REQUEST(400, "\u8bf7\u6c42\u53c2\u6570\u9519\u8bef"),
    UNAUTHORIZED(401, "\u672a\u8ba4\u8bc1"),
    FORBIDDEN(403, "\u65e0\u8bbf\u95ee\u6743\u9650"),
    NOT_FOUND(404, "\u8d44\u6e90\u4e0d\u5b58\u5728"),
    USERNAME_OR_PASSWORD_ERROR(1001, "\u7528\u6237\u540d\u6216\u5bc6\u7801\u9519\u8bef"),
    ACCOUNT_DISABLED(1002, "\u8d26\u53f7\u5df2\u7981\u7528"),
    TOKEN_INVALID(1003, "\u4ee4\u724c\u65e0\u6548\u6216\u5df2\u8fc7\u671f"),
    USER_EXISTS(1004, "\u7528\u6237\u5df2\u5b58\u5728"),
    ROLE_EXISTS(1005, "\u89d2\u8272\u5df2\u5b58\u5728"),
    NO_PERMISSION(1006, "\u65e0\u64cd\u4f5c\u6743\u9650"),
    RESOURCE_NOT_FOUND(1007, "\u8d44\u6e90\u4e0d\u5b58\u5728"),
    SERVER_ERROR(500, "\u670d\u52a1\u5668\u5185\u90e8\u9519\u8bef");

    private final int code;
    private final String message;

    private ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}

