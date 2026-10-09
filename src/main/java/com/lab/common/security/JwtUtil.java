package com.lab.common.security;

import com.lab.common.security.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    private final SecretKey key;
    private final long accessTtlMillis;
    private final long refreshTtlMillis;
    private final String issuer;

    public JwtUtil(JwtProperties props) {
        this.key = Keys.hmacShaKeyFor((byte[])props.getSecret().getBytes(StandardCharsets.UTF_8));
        this.accessTtlMillis = Duration.ofMinutes(props.getAccessTtlMinutes()).toMillis();
        this.refreshTtlMillis = Duration.ofDays(props.getRefreshTtlDays()).toMillis();
        this.issuer = props.getIssuer();
    }

    public String generateAccess(String jti, Long userId, String username, Long deptId, Long mentorGroupId, Set<String> perms, Set<String> roles) {
        Date now = new Date();
        return Jwts.builder().id(jti).subject(String.valueOf(userId)).claim("type", (Object)"access").claim("uname", (Object)username).claim("dept", (Object)deptId).claim("mg", (Object)mentorGroupId).claim("perms", perms).claim("roles", roles).issuer(this.issuer).issuedAt(now).expiration(new Date(now.getTime() + this.accessTtlMillis)).signWith((Key)this.key).compact();
    }

    public String generateRefresh(String jti, Long userId, String username) {
        Date now = new Date();
        return Jwts.builder().id(jti).subject(String.valueOf(userId)).claim("type", (Object)"refresh").claim("uname", (Object)username).issuer(this.issuer).issuedAt(now).expiration(new Date(now.getTime() + this.refreshTtlMillis)).signWith((Key)this.key).compact();
    }

    public long accessTtlSeconds() {
        return this.accessTtlMillis / 1000L;
    }

    public long refreshTtlSeconds() {
        return this.refreshTtlMillis / 1000L;
    }

    public Claims parse(String token) {
        return (Claims)Jwts.parser().verifyWith(this.key).build().parseSignedClaims((CharSequence)token).getPayload();
    }

    public static Set<String> readSet(Claims claims, String key) {
        Object v = claims.get(key);
        if (v instanceof List) {
            List<?> list = (List<?>)v;
            return Set.copyOf(list.stream().map(String::valueOf).toList());
        }
        return Set.of();
    }
}

