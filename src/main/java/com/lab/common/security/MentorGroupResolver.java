package com.lab.common.security;

public interface MentorGroupResolver {
    public String resolveNanoId(Long var1);

    public String resolveName(Long var1);

    public Long resolveMentorGroupId(String var1);
}

