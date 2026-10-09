package com.lab.module.rbac.service;

import com.lab.common.api.ResultCode;
import com.lab.common.exception.BizException;
import com.lab.common.security.JwtUtil;
import com.lab.common.security.SecurityContextUtil;
import com.lab.common.security.SessionStore;
import com.lab.common.security.UserPrincipal;
import com.lab.common.storage.FileStorageService;
import com.lab.module.rbac.dto.LoginRequest;
import com.lab.module.rbac.dto.LoginResponse;
import com.lab.module.rbac.dto.MeVo;
import com.lab.module.rbac.entity.CoreUser;
import com.lab.module.rbac.repository.CoreUserRepository;
import com.lab.module.rbac.service.PermissionLoader;
import io.jsonwebtoken.Claims;
import java.time.Duration;
import java.util.Set;
import java.util.UUID;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final CoreUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final PermissionLoader permissionLoader;
    private final SessionStore sessionStore;
    private final FileStorageService fileStorageService;

    public LoginResponse login(LoginRequest req) {
        CoreUser user = this.userRepository.findByUsername(req.getUsername());
        if (user == null || user.getPassword() == null || !this.passwordEncoder.matches((CharSequence)req.getPassword(), user.getPassword())) {
            throw new BizException(ResultCode.USERNAME_OR_PASSWORD_ERROR);
        }
        if (user.getIsActive() != null && user.getIsActive() == 0) {
            throw new BizException(ResultCode.ACCOUNT_DISABLED);
        }
        Set<String> perms = this.permissionLoader.loadPermissionCodes(user.getId());
        Set<String> roles = this.permissionLoader.loadRoleCodes(user.getId());
        String jti = UUID.randomUUID().toString();
        String access = this.jwtUtil.generateAccess(jti, user.getId(), user.getUsername(), user.getDeptId(), user.getMentorGroupId(), perms, roles);
        String refresh = this.jwtUtil.generateRefresh(jti, user.getId(), user.getUsername());
        this.sessionStore.create(jti, user.getId(), Duration.ofSeconds(this.jwtUtil.refreshTtlSeconds()));
        return LoginResponse.builder().nanoId(user.getNanoId()).username(user.getUsername()).accessToken(access).expiresIn(this.jwtUtil.accessTtlSeconds()).refreshToken(refresh).refreshExpiresIn(this.jwtUtil.refreshTtlSeconds()).tokenType("Bearer").permissions(perms).roles(roles).build();
    }

    public LoginResponse refresh(String refreshToken) {
        Claims claims;
        try {
            claims = this.jwtUtil.parse(refreshToken);
        }
        catch (Exception e) {
            throw new BizException(ResultCode.TOKEN_INVALID);
        }
        if (!"refresh".equals(claims.get("type", String.class))) {
            throw new BizException(ResultCode.TOKEN_INVALID);
        }
        String jti = claims.getId();
        if (!this.sessionStore.exists(jti)) {
            throw new BizException(ResultCode.TOKEN_INVALID);
        }
        CoreUser user = (CoreUser)this.userRepository.findById(Long.valueOf(claims.getSubject())).orElseThrow(() -> new BizException(ResultCode.RESOURCE_NOT_FOUND));
        Set<String> perms = this.permissionLoader.loadPermissionCodes(user.getId());
        Set<String> roles = this.permissionLoader.loadRoleCodes(user.getId());
        String access = this.jwtUtil.generateAccess(jti, user.getId(), user.getUsername(), user.getDeptId(), user.getMentorGroupId(), perms, roles);
        return LoginResponse.builder().nanoId(user.getNanoId()).username(user.getUsername()).accessToken(access).expiresIn(this.jwtUtil.accessTtlSeconds()).refreshToken(refreshToken).refreshExpiresIn(this.jwtUtil.refreshTtlSeconds()).tokenType("Bearer").permissions(perms).roles(roles).build();
    }

    public MeVo me() {
        UserPrincipal principal = SecurityContextUtil.current();
        if (principal == null) {
            throw new BizException(ResultCode.TOKEN_INVALID);
        }
        CoreUser user = (CoreUser)this.userRepository.findById(principal.getId()).orElseThrow(() -> new BizException(ResultCode.RESOURCE_NOT_FOUND));
        Set<String> perms = this.permissionLoader.loadPermissionCodes(user.getId());
        Set<String> roles = this.permissionLoader.loadRoleCodes(user.getId());
        MeVo vo = new MeVo();
        vo.setNanoId(user.getNanoId());
        vo.setUsername(user.getUsername());
        vo.setName(user.getName());
        vo.setDeptId(user.getDeptId());
        vo.setMentorGroupId(user.getMentorGroupId());
        vo.setIsActive(user.getIsActive());
        vo.setAvatarUrl(this.fileStorageService.avatarUrl(user.getAvatar()));
        vo.setPermissions(perms);
        vo.setRoles(roles);
        return vo;
    }

    public void logout() {
        UserPrincipal principal = SecurityContextUtil.current();
        if (principal == null || principal.getJti() == null) {
            return;
        }
        this.sessionStore.revoke(principal.getJti());
    }

    public AuthService(CoreUserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, PermissionLoader permissionLoader, SessionStore sessionStore, FileStorageService fileStorageService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.permissionLoader = permissionLoader;
        this.sessionStore = sessionStore;
        this.fileStorageService = fileStorageService;
    }
}

