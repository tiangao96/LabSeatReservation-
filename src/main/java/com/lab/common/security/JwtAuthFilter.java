package com.lab.common.security;

import com.lab.common.security.JwtUtil;
import com.lab.common.security.SessionStore;
import com.lab.common.security.UserPrincipal;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthFilter
extends OncePerRequestFilter {
    private static final String BEARER = "Bearer ";
    private final JwtUtil jwtUtil;
    private final SessionStore sessionStore;

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith(BEARER)) {
            String token = header.substring(BEARER.length());
            try {
                Claims claims = this.jwtUtil.parse(token);
                String jti = claims.getId();
                if ("access".equals(claims.get("type", String.class)) && this.sessionStore.exists(jti)) {
                    Long userId = Long.valueOf(claims.getSubject());
                    Object deptObj = claims.get((Object)"dept");
                    Long deptId = deptObj instanceof Number ? Long.valueOf(((Number)deptObj).longValue()) : null;
                    Object mgObj = claims.get((Object)"mg");
                    Long mentorGroupId = mgObj instanceof Number ? Long.valueOf(((Number)mgObj).longValue()) : null;
                    UserPrincipal principal = UserPrincipal.builder().id(userId).username((String)claims.get("uname", String.class)).nanoId(null).jti(jti).deptId(deptId).mentorGroupId(mentorGroupId).permissions(JwtUtil.readSet((Claims)claims, (String)"perms")).roles(JwtUtil.readSet((Claims)claims, (String)"roles")).build();
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken((Object)principal, null, principal.getAuthorities());
                    auth.setDetails((Object)new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication((Authentication)auth);
                } else {
                    SecurityContextHolder.clearContext();
                }
            }
            catch (Exception e) {
                SecurityContextHolder.clearContext();
            }
        }
        chain.doFilter((ServletRequest)request, (ServletResponse)response);
    }

    public JwtAuthFilter(JwtUtil jwtUtil, SessionStore sessionStore) {
        this.jwtUtil = jwtUtil;
        this.sessionStore = sessionStore;
    }
}

