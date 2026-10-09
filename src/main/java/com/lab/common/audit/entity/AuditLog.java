package com.lab.common.audit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name="audit_log")
public class AuditLog {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="nano_id", nullable=false, length=21)
    private String nanoId;
    @Column(name="user_id")
    private Long userId;
    private String username;
    @Column(name="dept_id")
    private Long deptId;
    @Column(nullable=false, length=32)
    private String module;
    @Column(nullable=false, length=32)
    private String action;
    @Column(length=128)
    private String description;
    @Column(name="target_id", length=64)
    private String targetId;
    @Column(name="http_method", length=10)
    private String httpMethod;
    @Column(name="request_uri", length=255)
    private String requestUri;
    @Column(length=64)
    private String ip;
    @Column(name="user_agent", length=255)
    private String userAgent;
    @Column(columnDefinition="TEXT")
    private String params;
    @Column(nullable=false, length=16)
    private String result;
    @Column(name="error_msg", columnDefinition="TEXT")
    private String errorMsg;
    @Column(name="duration_ms")
    private Integer durationMs;
    @Column(name="create_time", nullable=false)
    private LocalDateTime createTime;

    public AuditLog() {
    }

    public Long getId() {
        return this.id;
    }

    public String getNanoId() {
        return this.nanoId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public String getUsername() {
        return this.username;
    }

    public Long getDeptId() {
        return this.deptId;
    }

    public String getModule() {
        return this.module;
    }

    public String getAction() {
        return this.action;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTargetId() {
        return this.targetId;
    }

    public String getHttpMethod() {
        return this.httpMethod;
    }

    public String getRequestUri() {
        return this.requestUri;
    }

    public String getIp() {
        return this.ip;
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public String getParams() {
        return this.params;
    }

    public String getResult() {
        return this.result;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public Integer getDurationMs() {
        return this.durationMs;
    }

    public LocalDateTime getCreateTime() {
        return this.createTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNanoId(String nanoId) {
        this.nanoId = nanoId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public void setDurationMs(Integer durationMs) {
        this.durationMs = durationMs;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AuditLog)) {
            return false;
        }
        AuditLog other = (AuditLog)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
            return false;
        }
        Long this$userId = this.getUserId();
        Long other$userId = other.getUserId();
        if (this$userId == null ? other$userId != null : !((Object)this$userId).equals(other$userId)) {
            return false;
        }
        Long this$deptId = this.getDeptId();
        Long other$deptId = other.getDeptId();
        if (this$deptId == null ? other$deptId != null : !((Object)this$deptId).equals(other$deptId)) {
            return false;
        }
        Integer this$durationMs = this.getDurationMs();
        Integer other$durationMs = other.getDurationMs();
        if (this$durationMs == null ? other$durationMs != null : !((Object)this$durationMs).equals(other$durationMs)) {
            return false;
        }
        String this$nanoId = this.getNanoId();
        String other$nanoId = other.getNanoId();
        if (this$nanoId == null ? other$nanoId != null : !this$nanoId.equals(other$nanoId)) {
            return false;
        }
        String this$username = this.getUsername();
        String other$username = other.getUsername();
        if (this$username == null ? other$username != null : !this$username.equals(other$username)) {
            return false;
        }
        String this$module = this.getModule();
        String other$module = other.getModule();
        if (this$module == null ? other$module != null : !this$module.equals(other$module)) {
            return false;
        }
        String this$action = this.getAction();
        String other$action = other.getAction();
        if (this$action == null ? other$action != null : !this$action.equals(other$action)) {
            return false;
        }
        String this$description = this.getDescription();
        String other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description)) {
            return false;
        }
        String this$targetId = this.getTargetId();
        String other$targetId = other.getTargetId();
        if (this$targetId == null ? other$targetId != null : !this$targetId.equals(other$targetId)) {
            return false;
        }
        String this$httpMethod = this.getHttpMethod();
        String other$httpMethod = other.getHttpMethod();
        if (this$httpMethod == null ? other$httpMethod != null : !this$httpMethod.equals(other$httpMethod)) {
            return false;
        }
        String this$requestUri = this.getRequestUri();
        String other$requestUri = other.getRequestUri();
        if (this$requestUri == null ? other$requestUri != null : !this$requestUri.equals(other$requestUri)) {
            return false;
        }
        String this$ip = this.getIp();
        String other$ip = other.getIp();
        if (this$ip == null ? other$ip != null : !this$ip.equals(other$ip)) {
            return false;
        }
        String this$userAgent = this.getUserAgent();
        String other$userAgent = other.getUserAgent();
        if (this$userAgent == null ? other$userAgent != null : !this$userAgent.equals(other$userAgent)) {
            return false;
        }
        String this$params = this.getParams();
        String other$params = other.getParams();
        if (this$params == null ? other$params != null : !this$params.equals(other$params)) {
            return false;
        }
        String this$result = this.getResult();
        String other$result = other.getResult();
        if (this$result == null ? other$result != null : !this$result.equals(other$result)) {
            return false;
        }
        String this$errorMsg = this.getErrorMsg();
        String other$errorMsg = other.getErrorMsg();
        if (this$errorMsg == null ? other$errorMsg != null : !this$errorMsg.equals(other$errorMsg)) {
            return false;
        }
        LocalDateTime this$createTime = this.getCreateTime();
        LocalDateTime other$createTime = other.getCreateTime();
        return !(this$createTime == null ? other$createTime != null : !((Object)this$createTime).equals(other$createTime));
    }

    protected boolean canEqual(Object other) {
        return other instanceof AuditLog;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Long $userId = this.getUserId();
        result = result * 59 + ($userId == null ? 43 : ((Object)$userId).hashCode());
        Long $deptId = this.getDeptId();
        result = result * 59 + ($deptId == null ? 43 : ((Object)$deptId).hashCode());
        Integer $durationMs = this.getDurationMs();
        result = result * 59 + ($durationMs == null ? 43 : ((Object)$durationMs).hashCode());
        String $nanoId = this.getNanoId();
        result = result * 59 + ($nanoId == null ? 43 : $nanoId.hashCode());
        String $username = this.getUsername();
        result = result * 59 + ($username == null ? 43 : $username.hashCode());
        String $module = this.getModule();
        result = result * 59 + ($module == null ? 43 : $module.hashCode());
        String $action = this.getAction();
        result = result * 59 + ($action == null ? 43 : $action.hashCode());
        String $description = this.getDescription();
        result = result * 59 + ($description == null ? 43 : $description.hashCode());
        String $targetId = this.getTargetId();
        result = result * 59 + ($targetId == null ? 43 : $targetId.hashCode());
        String $httpMethod = this.getHttpMethod();
        result = result * 59 + ($httpMethod == null ? 43 : $httpMethod.hashCode());
        String $requestUri = this.getRequestUri();
        result = result * 59 + ($requestUri == null ? 43 : $requestUri.hashCode());
        String $ip = this.getIp();
        result = result * 59 + ($ip == null ? 43 : $ip.hashCode());
        String $userAgent = this.getUserAgent();
        result = result * 59 + ($userAgent == null ? 43 : $userAgent.hashCode());
        String $params = this.getParams();
        result = result * 59 + ($params == null ? 43 : $params.hashCode());
        String $result = this.getResult();
        result = result * 59 + ($result == null ? 43 : $result.hashCode());
        String $errorMsg = this.getErrorMsg();
        result = result * 59 + ($errorMsg == null ? 43 : $errorMsg.hashCode());
        LocalDateTime $createTime = this.getCreateTime();
        result = result * 59 + ($createTime == null ? 43 : ((Object)$createTime).hashCode());
        return result;
    }

    public String toString() {
        return "AuditLog(id=" + this.getId() + ", nanoId=" + this.getNanoId() + ", userId=" + this.getUserId() + ", username=" + this.getUsername() + ", deptId=" + this.getDeptId() + ", module=" + this.getModule() + ", action=" + this.getAction() + ", description=" + this.getDescription() + ", targetId=" + this.getTargetId() + ", httpMethod=" + this.getHttpMethod() + ", requestUri=" + this.getRequestUri() + ", ip=" + this.getIp() + ", userAgent=" + this.getUserAgent() + ", params=" + this.getParams() + ", result=" + this.getResult() + ", errorMsg=" + this.getErrorMsg() + ", durationMs=" + this.getDurationMs() + ", createTime=" + String.valueOf(this.getCreateTime()) + ")";
    }
}

