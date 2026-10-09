package com.lab.common.security;

import com.lab.common.security.DeptTreeNode;
import java.util.List;

public interface DeptResolver {
    public String resolveNanoId(Long var1);

    public String resolveName(Long var1);

    public Long resolveDeptId(String var1);

    public List<DeptTreeNode> deptTree(int var1);
}

