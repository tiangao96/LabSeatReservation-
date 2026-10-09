package com.lab.module.rbac.dto;

import com.lab.module.rbac.dto.UserImportResultVo;
import java.util.List;

public class UserImportResultVo {
    private int total;
    private int success;
    private int skipped;
    private int failure;
    private List<FailureItem> failures;

    public UserImportResultVo() {
    }

    public int getTotal() {
        return this.total;
    }

    public int getSuccess() {
        return this.success;
    }

    public int getSkipped() {
        return this.skipped;
    }

    public int getFailure() {
        return this.failure;
    }

    public List<FailureItem> getFailures() {
        return this.failures;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public void setSkipped(int skipped) {
        this.skipped = skipped;
    }

    public void setFailure(int failure) {
        this.failure = failure;
    }

    public void setFailures(List<FailureItem> failures) {
        this.failures = failures;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof UserImportResultVo)) {
            return false;
        }
        UserImportResultVo other = (UserImportResultVo)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        if (this.getTotal() != other.getTotal()) {
            return false;
        }
        if (this.getSuccess() != other.getSuccess()) {
            return false;
        }
        if (this.getSkipped() != other.getSkipped()) {
            return false;
        }
        if (this.getFailure() != other.getFailure()) {
            return false;
        }
        List this$failures = this.getFailures();
        List other$failures = other.getFailures();
        return !(this$failures == null ? other$failures != null : !((Object)this$failures).equals(other$failures));
    }

    protected boolean canEqual(Object other) {
        return other instanceof UserImportResultVo;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getTotal();
        result = result * 59 + this.getSuccess();
        result = result * 59 + this.getSkipped();
        result = result * 59 + this.getFailure();
        List $failures = this.getFailures();
        result = result * 59 + ($failures == null ? 43 : ((Object)$failures).hashCode());
        return result;
    }

    public String toString() {
        return "UserImportResultVo(total=" + this.getTotal() + ", success=" + this.getSuccess() + ", skipped=" + this.getSkipped() + ", failure=" + this.getFailure() + ", failures=" + String.valueOf(this.getFailures()) + ")";
    }

    public static class FailureItem {
        private int row;
        private String username;
        private String reason;

        public FailureItem() {
        }

        public FailureItem(int row, String username, String reason) {
            this.row = row;
            this.username = username;
            this.reason = reason;
        }

        public int getRow() {
            return this.row;
        }

        public String getUsername() {
            return this.username;
        }

        public String getReason() {
            return this.reason;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof FailureItem)) {
                return false;
            }
            FailureItem other = (FailureItem)o;
            if (!other.canEqual(this)) {
                return false;
            }
            if (this.getRow() != other.getRow()) {
                return false;
            }
            String this$username = this.getUsername();
            String other$username = other.getUsername();
            if (this$username == null ? other$username != null : !this$username.equals(other$username)) {
                return false;
            }
            String this$reason = this.getReason();
            String other$reason = other.getReason();
            return !(this$reason == null ? other$reason != null : !this$reason.equals(other$reason));
        }

        protected boolean canEqual(Object other) {
            return other instanceof FailureItem;
        }

        public int hashCode() {
            int PRIME = 59;
            int result = 1;
            result = result * 59 + this.getRow();
            String $username = this.getUsername();
            result = result * 59 + ($username == null ? 43 : $username.hashCode());
            String $reason = this.getReason();
            result = result * 59 + ($reason == null ? 43 : $reason.hashCode());
            return result;
        }

        public String toString() {
            return "UserImportResultVo.FailureItem(row=" + this.getRow() + ", username=" + this.getUsername() + ", reason=" + this.getReason() + ")";
        }
    }
}

