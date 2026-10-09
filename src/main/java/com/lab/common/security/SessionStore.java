package com.lab.common.security;

import java.time.Duration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class SessionStore {
    private static final String KEY_PREFIX = "auth:session:";
    private final StringRedisTemplate redis;

    public void create(String jti, Long userId, Duration ttl) {
        this.redis.opsForValue().set(KEY_PREFIX + jti, String.valueOf(userId), ttl);
    }

    public boolean exists(String jti) {
        if (jti == null || jti.isEmpty()) {
            return false;
        }
        return Boolean.TRUE.equals(this.redis.hasKey(KEY_PREFIX + jti));
    }

    public void revoke(String jti) {
        if (jti == null || jti.isEmpty()) {
            return;
        }
        this.redis.delete(KEY_PREFIX + jti);
    }

    public SessionStore(StringRedisTemplate redis) {
        this.redis = redis;
    }
}

