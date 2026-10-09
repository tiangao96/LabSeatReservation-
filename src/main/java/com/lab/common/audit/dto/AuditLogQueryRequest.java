package com.lab.common.audit.dto;

import java.time.LocalDateTime;

public class AuditLogQueryRequest {
    private String keyword;
    private String module;
    private String action;
    private String result;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public AuditLogQueryRequest() {
    }

    public String getKeyword() {
        return this.keyword;
    }

    public String getModule() {
        return this.module;
    }

    public String getAction() {
        return this.action;
    }

    public String getResult() {
        return this.result;
    }

    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AuditLogQueryRequest)) {
            return false;
        }
        AuditLogQueryRequest other = (AuditLogQueryRequest)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        String this$keyword = this.getKeyword();
        String other$keyword = other.getKeyword();
        if (this$keyword == null ? other$keyword != null : !this$keyword.equals(other$keyword)) {
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
        String this$result = this.getResult();
        String other$result = other.getResult();
        if (this$result == null ? other$result != null : !this$result.equals(other$result)) {
            return false;
        }
        LocalDateTime this$startTime = this.getStartTime();
        LocalDateTime other$startTime = other.getStartTime();
        if (this$startTime == null ? other$startTime != null : !((Object)this$startTime).equals(other$startTime)) {
            return false;
        }
        LocalDateTime this$endTime = this.getEndTime();
        LocalDateTime other$endTime = other.getEndTime();
        return !(this$endTime == null ? other$endTime != null : !((Object)this$endTime).equals(other$endTime));
    }

    protected boolean canEqual(Object other) {
        return other instanceof AuditLogQueryRequest;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $keyword = this.getKeyword();
        result = result * 59 + ($keyword == null ? 43 : $keyword.hashCode());
        String $module = this.getModule();
        result = result * 59 + ($module == null ? 43 : $module.hashCode());
        String $action = this.getAction();
        result = result * 59 + ($action == null ? 43 : $action.hashCode());
        String $result = this.getResult();
        result = result * 59 + ($result == null ? 43 : $result.hashCode());
        LocalDateTime $startTime = this.getStartTime();
        result = result * 59 + ($startTime == null ? 43 : ((Object)$startTime).hashCode());
        LocalDateTime $endTime = this.getEndTime();
        result = result * 59 + ($endTime == null ? 43 : ((Object)$endTime).hashCode());
        return result;
    }

    public String toString() {
        return "AuditLogQueryRequest(keyword=" + this.getKeyword() + ", module=" + this.getModule() + ", action=" + this.getAction() + ", result=" + this.getResult() + ", startTime=" + String.valueOf(this.getStartTime()) + ", endTime=" + String.valueOf(this.getEndTime()) + ")";
    }
}

