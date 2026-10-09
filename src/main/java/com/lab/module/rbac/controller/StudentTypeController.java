package com.lab.module.rbac.controller;

import com.lab.common.api.Result;
import com.lab.common.api.ResultCode;
import com.lab.common.exception.BizException;
import com.lab.module.rbac.entity.CoreUser;
import com.lab.module.rbac.repository.CoreUserRepository;
import java.util.HashMap;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/users")
public class StudentTypeController {
    private final CoreUserRepository userRepository;

    @PutMapping("/{nanoId}/student-type")
    @PreAuthorize(value = "@perm.has('system:user:update')")
    @Transactional
    public Result<Void> setStudentType(@PathVariable(name = "nanoId") String nanoId,
                                       @RequestBody Map<String, Object> body) {
        CoreUser u = this.userRepository.findByNanoId(nanoId);
        if (u == null) {
            throw new BizException(ResultCode.RESOURCE_NOT_FOUND, "用户不存在: " + nanoId);
        }
        Object raw = body.get("userType");
        Integer userType = null;
        if (raw != null && !String.valueOf(raw).isBlank()) {
            try {
                userType = Integer.valueOf(String.valueOf(raw).trim());
            } catch (NumberFormatException e) {
                throw new BizException(ResultCode.BAD_REQUEST, "userType 必须为 1(本科)/2(硕士)/3(博士)");
            }
            if (userType.intValue() < 1 || userType.intValue() > 3) {
                throw new BizException(ResultCode.BAD_REQUEST, "userType 必须为 1(本科)/2(硕士)/3(博士)");
            }
        }
        u.setUserType(userType);
        this.userRepository.save(u);
        return Result.ok();
    }

    @GetMapping("/{nanoId}/student-type")
    @PreAuthorize(value = "@perm.hasAny('system:user:list','system:user:view')")
    public Result<Map<String, Object>> getStudentType(@PathVariable(name = "nanoId") String nanoId) {
        CoreUser u = this.userRepository.findByNanoId(nanoId);
        if (u == null) {
            throw new BizException(ResultCode.RESOURCE_NOT_FOUND, "用户不存在: " + nanoId);
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("userType", u.getUserType() == null ? Integer.valueOf(0) : u.getUserType());
        return Result.ok(result);
    }

    public StudentTypeController(CoreUserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
