package com.lab.common.api;

import java.util.List;
import org.springframework.data.domain.Page;

public class PageResult<T> {
    private List<T> records;
    private long total;
    private long page;
    private long size;

    public static <T> PageResult<T> of(Page<T> page) {
        return new PageResult(page.getContent(), page.getTotalElements(), (long)(page.getNumber() + 1), (long)page.getSize());
    }

    public List<T> getRecords() {
        return this.records;
    }

    public long getTotal() {
        return this.total;
    }

    public long getPage() {
        return this.page;
    }

    public long getSize() {
        return this.size;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PageResult)) {
            return false;
        }
        PageResult other = (PageResult)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        if (this.getTotal() != other.getTotal()) {
            return false;
        }
        if (this.getPage() != other.getPage()) {
            return false;
        }
        if (this.getSize() != other.getSize()) {
            return false;
        }
        List this$records = this.getRecords();
        List other$records = other.getRecords();
        return !(this$records == null ? other$records != null : !((Object)this$records).equals(other$records));
    }

    protected boolean canEqual(Object other) {
        return other instanceof PageResult;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        long $total = this.getTotal();
        result = result * 59 + (int)($total >>> 32 ^ $total);
        long $page = this.getPage();
        result = result * 59 + (int)($page >>> 32 ^ $page);
        long $size = this.getSize();
        result = result * 59 + (int)($size >>> 32 ^ $size);
        List $records = this.getRecords();
        result = result * 59 + ($records == null ? 43 : ((Object)$records).hashCode());
        return result;
    }

    public String toString() {
        return "PageResult(records=" + String.valueOf(this.getRecords()) + ", total=" + this.getTotal() + ", page=" + this.getPage() + ", size=" + this.getSize() + ")";
    }

    public PageResult() {
    }

    public PageResult(List<T> records, long total, long page, long size) {
        this.records = records;
        this.total = total;
        this.page = page;
        this.size = size;
    }
}

