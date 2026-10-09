package com.lab.module.rbac.service;

import cn.idev.excel.FastExcel;
import cn.idev.excel.read.builder.ExcelReaderBuilder;
import com.lab.common.api.PageResult;
import com.lab.common.api.ResultCode;
import com.lab.common.entity.BaseEntity;
import com.lab.common.exception.BizException;
import com.lab.common.storage.FileStorageService;
import com.lab.module.rbac.dto.AvatarVo;
import com.lab.module.rbac.dto.UserCreateRequest;
import com.lab.module.rbac.dto.UserImportResultVo;
import com.lab.module.rbac.dto.UserImportRow;
import com.lab.module.rbac.dto.UserUpdateRequest;
import com.lab.module.rbac.dto.UserVo;
import com.lab.module.rbac.entity.CoreDept;
import com.lab.module.rbac.entity.CoreMentorGroup;
import com.lab.module.rbac.entity.CoreRole;
import com.lab.module.rbac.entity.CoreUser;
import com.lab.module.rbac.entity.CoreUserRole;
import com.lab.module.rbac.repository.CoreDeptRepository;
import com.lab.module.rbac.repository.CoreMentorGroupRepository;
import com.lab.module.rbac.repository.CoreRoleRepository;
import com.lab.module.rbac.repository.CoreUserRepository;
import com.lab.module.rbac.repository.CoreUserRoleRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.multipart.MultipartFile;

/*
 * Exception performing whole class analysis ignored.
 */
@Service
public class UserService {
    private final CoreUserRepository userRepository;
    private final CoreUserRoleRepository userRoleRepository;
    private final CoreRoleRepository roleRepository;
    private final CoreDeptRepository deptRepository;
    private final CoreMentorGroupRepository mentorGroupRepository;
    private final PasswordEncoder passwordEncoder;
    private final FileStorageService fileStorageService;
    private final PlatformTransactionManager transactionManager;
    private static final int MAX_IMPORT_ROWS = 500;
    private static final int MAX_FAILURES = 50;

    public PageResult<UserVo> page(long current, long size, String keyword, String deptNanoId, String mentorGroupNanoId) {
        PageRequest pageable = PageRequest.of((int)((int)Math.max(1L, current) - 1), (int)((int)size));
        Long mentorGroupId = null;
        if (mentorGroupNanoId != null && !mentorGroupNanoId.isBlank()) {
            CoreMentorGroup g = this.mentorGroupRepository.findByNanoId(mentorGroupNanoId);
            if (g == null) {
                throw new BizException(ResultCode.BAD_REQUEST, "\u5bfc\u5e08\u7ec4\u4e0d\u5b58\u5728: " + mentorGroupNanoId);
            }
            mentorGroupId = g.getId();
        }
        Long deptId = null;
        if (deptNanoId != null && !deptNanoId.isBlank()) {
            CoreDept d = this.deptRepository.findByNanoId(deptNanoId);
            if (d == null) {
                throw new BizException(ResultCode.BAD_REQUEST, "\u90e8\u95e8\u4e0d\u5b58\u5728: " + deptNanoId);
            }
            deptId = d.getId();
        }
        Page<CoreUser> p = mentorGroupId != null ? this.userRepository.searchByMentorGroupAndKeyword(mentorGroupId, keyword, (Pageable)pageable) : this.userRepository.searchByDeptAndKeyword(deptId, keyword, (Pageable)pageable);
        return PageResult.of(p.map(this::toVo));
    }

    public UserVo getByNanoId(String nanoId) {
        return this.toVo(this.requireUser(nanoId));
    }

    @Transactional
    public String create(UserCreateRequest req) {
        if (this.userRepository.findByUsername(req.getUsername()) != null) {
            throw new BizException(ResultCode.USER_EXISTS);
        }
        Long deptId = this.resolveDeptId(req.getDeptNanoId());
        Long mentorGroupId = this.resolveMentorGroupId(req.getMentorGroupNanoId());
        List<Long> roleIds = this.resolveRoleIds(req.getRoleNanoIds());
        return this.doCreateUser(req.getUsername(), req.getPassword(), req.getName(), req.getEmail(), req.getMobile(), deptId, mentorGroupId, roleIds);
    }

    private String doCreateUser(String username, String password, String name, String email, String mobile, Long deptId, Long mentorGroupId, List<Long> roleIds) {
        CoreUser u = new CoreUser();
        u.setUsername(username);
        u.setPassword(this.passwordEncoder.encode((CharSequence)password));
        u.setName(name);
        u.setEmail(email);
        u.setMobile(mobile);
        u.setDeptId(deptId);
        u.setMentorGroupId(mentorGroupId);
        u.setIsActive(Integer.valueOf(1));
        u.setIsSuperuser(Integer.valueOf(0));
        u = (CoreUser)this.userRepository.save(u);
        if (roleIds != null && !roleIds.isEmpty()) {
            for (Long roleId : roleIds) {
                this.userRoleRepository.save(new CoreUserRole(u.getId(), roleId));
            }
        }
        return u.getNanoId();
    }

    private List<Long> resolveRoleIds(List<String> roleNanoIds) {
        if (roleNanoIds == null || roleNanoIds.isEmpty()) {
            return List.of();
        }
        return this.roleRepository.findAllByNanoIdIn(roleNanoIds).stream().map(BaseEntity::getId).toList();
    }

    @Transactional
    public void update(String nanoId, UserUpdateRequest req) {
        CoreUser u = this.requireUser(nanoId);
        if (!u.getUsername().equals(req.getUsername())) {
            CoreUser other = this.userRepository.findByUsername(req.getUsername());
            if (other != null && !other.getId().equals(u.getId())) {
                throw new BizException(ResultCode.USER_EXISTS);
            }
            u.setUsername(req.getUsername());
        }
        if (req.getPassword() != null && !req.getPassword().isBlank()) {
            u.setPassword(this.passwordEncoder.encode((CharSequence)req.getPassword()));
        }
        u.setName(req.getName());
        u.setEmail(req.getEmail());
        u.setMobile(req.getMobile());
        u.setDeptId(this.resolveDeptId(req.getDeptNanoId()));
        u.setMentorGroupId(this.resolveMentorGroupId(req.getMentorGroupNanoId()));
        if (req.getIsActive() != null) {
            u.setIsActive(req.getIsActive());
        }
        this.userRepository.save(u);
    }

    @Transactional
    public void delete(String nanoId) {
        CoreUser u = this.requireUser(nanoId);
        if (this.userRoleRepository.existsByUserId(u.getId())) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u7528\u6237\u5df2\u5206\u914d\u89d2\u8272\uff0c\u8bf7\u5148\u89e3\u9664");
        }
        u.setIsDeleted(Integer.valueOf(1));
        this.userRepository.save(u);
    }

    @Transactional
    public void assignRoles(String nanoId, List<String> roleNanoIds) {
        CoreUser u = this.requireUser(nanoId);
        this.userRoleRepository.deleteByUserId(u.getId());
        if (roleNanoIds == null || roleNanoIds.isEmpty()) {
            return;
        }
        List<CoreRole> roles = this.roleRepository.findAllByNanoIdIn(roleNanoIds);
        for (CoreRole r : roles) {
            this.userRoleRepository.save(new CoreUserRole(u.getId(), r.getId()));
        }
    }

    @Transactional
    public void changeDept(String nanoId, String deptNanoId) {
        CoreUser u = this.requireUser(nanoId);
        u.setDeptId(this.resolveDeptId(deptNanoId));
        this.userRepository.save(u);
    }

    @Transactional
    public void changeMentorGroup(String nanoId, String mentorGroupNanoId) {
        CoreUser u = this.requireUser(nanoId);
        u.setMentorGroupId(this.resolveMentorGroupId(mentorGroupNanoId));
        this.userRepository.save(u);
    }

    @Transactional
    public AvatarVo uploadAvatar(Long userId, MultipartFile file) {
        CoreUser u = (CoreUser)this.userRepository.findById(userId).orElseThrow(() -> new BizException(ResultCode.RESOURCE_NOT_FOUND));
        return this.persistAvatar(u, file);
    }

    @Transactional
    public AvatarVo uploadAvatar(String nanoId, MultipartFile file) {
        return this.persistAvatar(this.requireUser(nanoId), file);
    }

    @Transactional
    public void removeAvatar(Long userId) {
        this.userRepository.findById(userId).ifPresent(this::clearAvatar);
    }

    @Transactional
    public void removeAvatar(String nanoId) {
        this.clearAvatar(this.requireUser(nanoId));
    }

    private AvatarVo persistAvatar(CoreUser u, MultipartFile file) {
        FileStorageService.StoredAvatar stored = this.fileStorageService.storeAvatar(file);
        String oldUuid = u.getAvatar();
        u.setAvatar(stored.uuid());
        this.userRepository.save(u);
        if (oldUuid != null && !oldUuid.equals(stored.uuid())) {
            this.fileStorageService.deleteAvatar(oldUuid);
        }
        AvatarVo vo = new AvatarVo();
        vo.setAvatarUrl(stored.url());
        return vo;
    }

    private void clearAvatar(CoreUser u) {
        String oldUuid = u.getAvatar();
        if (oldUuid == null || oldUuid.isBlank()) {
            return;
        }
        u.setAvatar(null);
        this.userRepository.save(u);
        this.fileStorageService.deleteAvatar(oldUuid);
    }

    private CoreUser requireUser(String nanoId) {
        CoreUser u = this.userRepository.findByNanoId(nanoId);
        if (u == null) {
            throw new BizException(ResultCode.RESOURCE_NOT_FOUND);
        }
        return u;
    }

    private Long resolveDeptId(String deptNanoId) {
        if (deptNanoId == null || deptNanoId.isBlank()) {
            return null;
        }
        CoreDept d = this.deptRepository.findByNanoId(deptNanoId);
        if (d == null) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u90e8\u95e8\u4e0d\u5b58\u5728: " + deptNanoId);
        }
        return d.getId();
    }

    private Long resolveMentorGroupId(String mentorGroupNanoId) {
        if (mentorGroupNanoId == null || mentorGroupNanoId.isBlank()) {
            return null;
        }
        CoreMentorGroup g = this.mentorGroupRepository.findByNanoId(mentorGroupNanoId);
        if (g == null) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u5bfc\u5e08\u7ec4\u4e0d\u5b58\u5728: " + mentorGroupNanoId);
        }
        return g.getId();
    }

    private UserVo toVo(CoreUser u) {
        List<Long> roleIds;
        UserVo vo = new UserVo();
        vo.setNanoId(u.getNanoId());
        vo.setUsername(u.getUsername());
        vo.setName(u.getName());
        vo.setEmail(u.getEmail());
        vo.setMobile(u.getMobile());
        vo.setIsActive(u.getIsActive());
        vo.setSysCreateDatetime(u.getSysCreateDatetime());
        vo.setAvatarUrl(this.fileStorageService.avatarUrl(u.getAvatar()));
        if (u.getDeptId() != null) {
            this.deptRepository.findById(u.getDeptId()).ifPresent(d -> vo.setDeptNanoId(d.getNanoId()));
        }
        if (u.getMentorGroupId() != null) {
            this.mentorGroupRepository.findById(u.getMentorGroupId()).ifPresent(g -> vo.setMentorGroupNanoId(g.getNanoId()));
        }
        if ((roleIds = this.userRoleRepository.findRoleIdsByUserId(u.getId())).isEmpty()) {
            vo.setRoleNanoIds(List.of());
        } else {
            vo.setRoleNanoIds(this.roleRepository.findAllById(roleIds).stream().map(BaseEntity::getNanoId).toList());
        }
        return vo;
    }

    public UserImportResultVo importUsers(MultipartFile file) {
        List<UserImportRow> rows;
        this.validateExcel(file);
        try (InputStream in = file.getInputStream();){
            rows = ((ExcelReaderBuilder)FastExcel.read((InputStream)in).head(UserImportRow.class)).sheet().doReadSync();
        }
        catch (IOException e) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u6587\u4ef6\u89e3\u6790\u5931\u8d25: " + e.getMessage());
        }
        if (rows.isEmpty()) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u6587\u4ef6\u65e0\u6570\u636e\u884c");
        }
        if (rows.size() > 500) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u5355\u6b21\u6700\u591a\u5bfc\u5165 500 \u884c\uff0c\u5f53\u524d " + rows.size() + " \u884c");
        }
        Set<String> usernames = rows.stream().map(UserImportRow::getUsername).filter(s -> s != null && !s.isBlank()).collect(Collectors.toSet());
        Set<String> existing = usernames.isEmpty() ? Set.of() : this.userRepository.findByUsernameIn(usernames).stream().map(CoreUser::getUsername).collect(Collectors.toSet());
        Set<String> deptNames = rows.stream().map(UserImportRow::getDeptName).filter(s -> s != null && !s.isBlank()).collect(Collectors.toSet());
        LinkedHashMap<String, List<CoreDept>> deptByName = new LinkedHashMap<String, List<CoreDept>>();
        if (!deptNames.isEmpty()) {
            for (CoreDept d : this.deptRepository.findAllByNameIn(deptNames)) {
                deptByName.computeIfAbsent(d.getName(), k -> new ArrayList()).add(d);
            }
        }
        Set<String> mentorGroupNames = rows.stream().map(UserImportRow::getMentorGroupName).filter(s -> s != null && !s.isBlank()).collect(Collectors.toSet());
        LinkedHashMap<String, List<CoreMentorGroup>> mentorGroupByName = new LinkedHashMap<String, List<CoreMentorGroup>>();
        if (!mentorGroupNames.isEmpty()) {
            for (CoreMentorGroup g : this.mentorGroupRepository.findAllByNameIn(mentorGroupNames)) {
                mentorGroupByName.computeIfAbsent(g.getName(), k -> new ArrayList()).add(g);
            }
        }
        HashSet<String> roleCodes = new HashSet<String>();
        for (UserImportRow r : rows) {
            if (r.getRoleCodes() == null || r.getRoleCodes().isBlank()) continue;
            for (String c : r.getRoleCodes().split("\\|")) {
                String t = c.trim();
                if (t.isEmpty()) continue;
                roleCodes.add(t);
            }
        }
        LinkedHashMap<String, Long> roleIdByCode = new LinkedHashMap<String, Long>();
        if (!roleCodes.isEmpty()) {
            for (CoreRole r : this.roleRepository.findAllByCodeIn(roleCodes)) {
                roleIdByCode.put(r.getCode(), r.getId());
            }
        }
        UserImportResultVo result = new UserImportResultVo();
        result.setTotal(rows.size());
        ArrayList<UserImportResultVo.FailureItem> failures = new ArrayList<UserImportResultVo.FailureItem>();
        int success = 0;
        int skipped = 0;
        int failure = 0;
        HashSet<String> seenInBatch = new HashSet<String>();
        for (int i = 0; i < rows.size(); ++i) {
            UserImportRow row = (UserImportRow)rows.get(i);
            int rowNum = i + 2;
            String username = UserService.trim((String)row.getUsername());
            if (username == null || username.isEmpty() || UserService.isBlank((String)row.getPassword())) {
                ++failure;
                this.addFailure(failures, rowNum, username, "\u7528\u6237\u540d\u548c\u5bc6\u7801\u5fc5\u586b");
                continue;
            }
            if (existing.contains(username) || !seenInBatch.add(username)) {
                ++skipped;
                continue;
            }
            Long deptId = null;
            String deptName = UserService.trim((String)row.getDeptName());
            if (deptName != null && !deptName.isEmpty()) {
                List<CoreDept> depts = deptByName.get(deptName);
                if (depts == null || depts.isEmpty()) {
                    ++failure;
                    this.addFailure(failures, rowNum, username, "\u90e8\u95e8\u4e0d\u5b58\u5728: " + deptName);
                    continue;
                }
                if (depts.size() > 1) {
                    ++failure;
                    this.addFailure(failures, rowNum, username, "\u90e8\u95e8\u540d\u79f0\u91cd\u590d: " + deptName + "\uff0c\u8bf7\u4f7f\u7528\u90e8\u95e8\u7f16\u7801");
                    continue;
                }
                deptId = depts.get(0).getId();
            }
            Long mentorGroupId = null;
            String mentorGroupName = UserService.trim((String)row.getMentorGroupName());
            if (mentorGroupName != null && !mentorGroupName.isEmpty()) {
                List<CoreMentorGroup> groups = mentorGroupByName.get(mentorGroupName);
                if (groups == null || groups.isEmpty()) {
                    ++failure;
                    this.addFailure(failures, rowNum, username, "\u5bfc\u5e08\u7ec4\u4e0d\u5b58\u5728: " + mentorGroupName);
                    continue;
                }
                if (groups.size() > 1) {
                    ++failure;
                    this.addFailure(failures, rowNum, username, "\u5bfc\u5e08\u7ec4\u540d\u79f0\u91cd\u590d: " + mentorGroupName + "\uff0c\u8bf7\u4f7f\u7528\u5bfc\u5e08\u7ec4\u7f16\u7801");
                    continue;
                }
                mentorGroupId = groups.get(0).getId();
            }
            ArrayList<Long> roleIds = new ArrayList<Long>();
            String roleCodesStr = UserService.trim((String)row.getRoleCodes());
            boolean roleError = false;
            if (roleCodesStr != null && !roleCodesStr.isEmpty()) {
                for (String c : roleCodesStr.split("\\|")) {
                    String t = c.trim();
                    if (t.isEmpty()) continue;
                    Long rid = (Long)roleIdByCode.get(t);
                    if (rid == null) {
                        ++failure;
                        this.addFailure(failures, rowNum, username, "\u89d2\u8272\u7f16\u7801\u4e0d\u5b58\u5728: " + t);
                        roleError = true;
                        break;
                    }
                    roleIds.add(rid);
                }
            }
            if (roleError) continue;
            Long fDeptId = deptId;
            Long fMentorGroupId = mentorGroupId;
            ArrayList<Long> fRoleIds = roleIds;
            try {
                new TransactionTemplate(this.transactionManager).executeWithoutResult(status -> this.doCreateUser(username, row.getPassword(), UserService.trim((String)row.getName()), UserService.trim((String)row.getEmail()), UserService.trim((String)row.getMobile()), fDeptId, fMentorGroupId, fRoleIds));
                ++success;
                continue;
            }
            catch (Exception e) {
                ++failure;
                this.addFailure(failures, rowNum, username, "\u521b\u5efa\u5931\u8d25: " + this.rootMessage((Throwable)e));
            }
        }
        result.setSuccess(success);
        result.setSkipped(skipped);
        result.setFailure(failure);
        result.setFailures(failures.size() > 50 ? failures.subList(0, 50) : failures);
        return result;
    }

    public void downloadTemplate(HttpServletResponse response) {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=user-import-template.xlsx");
        try (ServletOutputStream out = response.getOutputStream();){
            List<UserImportRow> samples = List.of(this.buildSample("zhangsan", "Init@123", "\u5f20\u4e09", "zhangsan@example.com", "13800138000", "\u7814\u53d1\u90e8", "\u738b\u6559\u6388\u8bfe\u9898\u7ec4", "user"), this.buildSample("lisi", "Init@123", "\u674e\u56db", "lisi@example.com", "13900139000", "\u8fd0\u8425\u90e8", "\u674e\u6559\u6388\u8bfe\u9898\u7ec4", "admin|user"));
            FastExcel.write((OutputStream)out, UserImportRow.class).sheet("\u7528\u6237\u5bfc\u5165").doWrite(samples);
        }
        catch (IOException e) {
            throw new BizException(ResultCode.SERVER_ERROR, "\u6a21\u677f\u751f\u6210\u5931\u8d25: " + e.getMessage());
        }
    }

    private UserImportRow buildSample(String username, String password, String name, String email, String mobile, String deptName, String mentorGroupName, String roleCodes) {
        UserImportRow r = new UserImportRow();
        r.setUsername(username);
        r.setPassword(password);
        r.setName(name);
        r.setEmail(email);
        r.setMobile(mobile);
        r.setDeptName(deptName);
        r.setMentorGroupName(mentorGroupName);
        r.setRoleCodes(roleCodes);
        return r;
    }

    private void validateExcel(MultipartFile file) {
        boolean ok;
        if (file == null || file.isEmpty()) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u6587\u4ef6\u4e3a\u7a7a");
        }
        String ct = file.getContentType();
        String fn = file.getOriginalFilename();
        boolean bl = ok = ct != null && ct.contains("spreadsheet") || fn != null && fn.toLowerCase().endsWith(".xlsx");
        if (!ok) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u4ec5\u652f\u6301 .xlsx \u683c\u5f0f");
        }
    }

    private void addFailure(List<UserImportResultVo.FailureItem> failures, int row, String username, String reason) {
        failures.add(new UserImportResultVo.FailureItem(row, username, reason));
    }

    private String rootMessage(Throwable e) {
        Throwable c;
        for (c = e; c.getCause() != null && c.getCause() != c; c = c.getCause()) {
        }
        String msg = c.getMessage();
        return msg != null ? msg : c.getClass().getSimpleName();
    }

    private static String trim(String s) {
        return s == null ? null : s.trim();
    }

    private static boolean isBlank(String s) {
        return s == null || s.isBlank();
    }

    public UserService(CoreUserRepository userRepository, CoreUserRoleRepository userRoleRepository, CoreRoleRepository roleRepository, CoreDeptRepository deptRepository, CoreMentorGroupRepository mentorGroupRepository, PasswordEncoder passwordEncoder, FileStorageService fileStorageService, PlatformTransactionManager transactionManager) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.roleRepository = roleRepository;
        this.deptRepository = deptRepository;
        this.mentorGroupRepository = mentorGroupRepository;
        this.passwordEncoder = passwordEncoder;
        this.fileStorageService = fileStorageService;
        this.transactionManager = transactionManager;
    }
}

