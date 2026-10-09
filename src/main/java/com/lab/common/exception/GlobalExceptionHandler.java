package com.lab.common.exception;

import com.lab.common.api.Result;
import com.lab.common.api.ResultCode;
import com.lab.common.exception.BizException;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value={BizException.class})
    public Result<Void> handleBiz(BizException e) {
        log.warn("biz exception: {}", (Object)e.getMessage());
        return Result.fail((ResultCode)e.getCode(), (String)e.getMessage());
    }

    @ExceptionHandler(value={MethodArgumentNotValidException.class})
    public Result<Void> handleValid(MethodArgumentNotValidException e) {
        FieldError fe = e.getBindingResult().getFieldError();
        String msg = fe != null ? fe.getField() + ": " + fe.getDefaultMessage() : "\u53c2\u6570\u6821\u9a8c\u5931\u8d25";
        return Result.fail((ResultCode)ResultCode.BAD_REQUEST, (String)msg);
    }

    @ExceptionHandler(value={BindException.class})
    public Result<Void> handleBind(BindException e) {
        FieldError fe = e.getBindingResult().getFieldError();
        String msg = fe != null ? fe.getField() + ": " + fe.getDefaultMessage() : "\u53c2\u6570\u7ed1\u5b9a\u5931\u8d25";
        return Result.fail((ResultCode)ResultCode.BAD_REQUEST, (String)msg);
    }

    @ExceptionHandler(value={MaxUploadSizeExceededException.class})
    public Result<Void> handleUploadSize(MaxUploadSizeExceededException e) {
        log.warn("upload size exceeded: {}", (Object)e.getMessage());
        return Result.fail((ResultCode)ResultCode.BAD_REQUEST, (String)"\u6587\u4ef6\u8fc7\u5927\uff0c\u5df2\u8d85\u8fc7\u4e0a\u4f20\u4e0a\u9650");
    }

    @ExceptionHandler(value={NoResourceFoundException.class})
    public Result<Void> handleNoResource(NoResourceFoundException e, HttpServletResponse resp) {
        resp.setStatus(HttpStatus.NOT_FOUND.value());
        return Result.fail((ResultCode)ResultCode.NOT_FOUND, (String)"\u8d44\u6e90\u4e0d\u5b58\u5728");
    }

    @ExceptionHandler(value={AuthenticationException.class})
    public Result<Void> handleAuth(AuthenticationException e, HttpServletResponse resp) {
        resp.setStatus(HttpStatus.UNAUTHORIZED.value());
        return Result.fail((ResultCode)ResultCode.TOKEN_INVALID, (String)e.getMessage());
    }

    @ExceptionHandler(value={AccessDeniedException.class})
    public Result<Void> handleAccessDenied(AccessDeniedException e, HttpServletResponse resp) {
        resp.setStatus(HttpStatus.FORBIDDEN.value());
        return Result.fail((ResultCode)ResultCode.NO_PERMISSION);
    }

    @ExceptionHandler(value={Exception.class})
    public Result<Void> handleOther(Exception e) {
        log.error("unhandled exception", (Throwable)e);
        return Result.fail((ResultCode)ResultCode.SERVER_ERROR, (String)e.getMessage());
    }
}

