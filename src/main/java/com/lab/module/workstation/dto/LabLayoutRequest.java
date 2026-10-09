package com.lab.module.workstation.dto;

import com.lab.module.workstation.dto.WorkstationLayoutItem;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class LabLayoutRequest {
    @NotNull(message="\u5e03\u5c40\u9879\u5217\u8868\u4e0d\u80fd\u4e3a null\uff08\u7a7a\u5217\u8868\u8868\u793a\u6e05\u7a7a\u6240\u6709\u5750\u6807\uff09")
    @Valid
    private List<WorkstationLayoutItem> items;

    public LabLayoutRequest() {
    }

    public List<WorkstationLayoutItem> getItems() {
        return this.items;
    }

    public void setItems(List<WorkstationLayoutItem> items) {
        this.items = items;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof LabLayoutRequest)) {
            return false;
        }
        LabLayoutRequest other = (LabLayoutRequest)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        List this$items = this.getItems();
        List other$items = other.getItems();
        return !(this$items == null ? other$items != null : !((Object)this$items).equals(other$items));
    }

    protected boolean canEqual(Object other) {
        return other instanceof LabLayoutRequest;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        List $items = this.getItems();
        result = result * 59 + ($items == null ? 43 : ((Object)$items).hashCode());
        return result;
    }

    public String toString() {
        return "LabLayoutRequest(items=" + String.valueOf(this.getItems()) + ")";
    }
}

