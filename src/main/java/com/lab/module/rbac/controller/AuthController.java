package com.lab.module.rbac.controller;

import com.lab.common.api.Result;
import com.lab.common.audit.AuditLog;
import com.lab.common.security.crypto.RsaCryptoProperties;
import com.lab.module.rbac.dto.LoginRequest;
import com.lab.module.rbac.dto.LoginResponse;
import com.lab.module.rbac.dto.MeVo;
import com.lab.module.rbac.dto.RefreshRequest;
import com.lab.module.rbac.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="\u8ba4\u8bc1")
@RestController
@RequestMapping(value={"/api/auth"})
public class AuthController {
    private final AuthService authService;
    private final RsaCryptoProperties rsaCryptoProperties;

    @GetMapping(value={"/public-key"})
    public Result<String> publicKey() {
        return Result.ok((this.rsaCryptoProperties.getPublicKey() == null ? "" : this.rsaCryptoProperties.getPublicKey()));
    }

    @PostMapping(value={"/login"})
    @AuditLog(module="auth", action="login", description="\u7528\u6237\u767b\u5f55")
    public Result<LoginResponse> login(@RequestBody @Valid LoginRequest req) {
        return Result.ok(this.authService.login(req));
    }

    @PostMapping(value={"/refresh"})
    @AuditLog(module="auth", action="refresh", description="\u5237\u65b0\u4ee4\u724c")
    public Result<LoginResponse> refresh(@RequestBody @Valid RefreshRequest req) {
        return Result.ok(this.authService.refresh(req.getRefreshToken()));
    }

    @PostMapping(value={"/logout"})
    @AuditLog(module="auth", action="logout", description="\u7528\u6237\u767b\u51fa")
    public Result<Void> logout() {
        this.authService.logout();
        return Result.ok();
    }

    @GetMapping(value={"/me"})
    public Result<MeVo> me() {
        return Result.ok(this.authService.me());
    }

    public AuthController(AuthService authService, RsaCryptoProperties rsaCryptoProperties) {
        this.authService = authService;
        this.rsaCryptoProperties = rsaCryptoProperties;
    }
}

