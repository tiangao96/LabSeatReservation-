package com.lab.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab.common.api.Result;
import com.lab.common.api.ResultCode;
import com.lab.common.security.JwtAuthFilter;
import com.lab.common.security.crypto.LoginDecryptFilter;
import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled=true)
public class SecurityConfig {
    private final JwtAuthFilter jwtAuthFilter;
    private final LoginDecryptFilter loginDecryptFilter;
    private final ObjectMapper objectMapper;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()).cors(cors -> cors.configurationSource(this.corsConfigurationSource())).sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).authorizeHttpRequests(auth -> ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)auth.requestMatchers(new String[]{"/api/auth/login", "/api/auth/refresh", "/api/auth/public-key", "/uploads/**", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "/api/public/**"})).permitAll().anyRequest()).authenticated()).exceptionHandling(eh -> eh.authenticationEntryPoint((req, resp, e) -> this.writeJson(resp, Result.fail((ResultCode)ResultCode.TOKEN_INVALID, (String)"\u4ee4\u724c\u65e0\u6548\u6216\u5df2\u8fc7\u671f\uff0c\u8bf7\u91cd\u65b0\u767b\u5f55"), 401)).accessDeniedHandler((req, resp, e) -> this.writeJson(resp, Result.fail((ResultCode)ResultCode.NO_PERMISSION, (String)"\u65e0\u8bbf\u95ee\u6743\u9650"), 403))).addFilterBefore((Filter)this.loginDecryptFilter, UsernamePasswordAuthenticationFilter.class).addFilterBefore((Filter)this.jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return (SecurityFilterChain)http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration cfg = new CorsConfiguration();
        cfg.setAllowedOriginPatterns(List.of("*"));
        cfg.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        cfg.setAllowedHeaders(List.of("*"));
        cfg.setAllowCredentials(Boolean.valueOf(true));
        UrlBasedCorsConfigurationSource src = new UrlBasedCorsConfigurationSource();
        src.registerCorsConfiguration("/**", cfg);
        return src;
    }

    private void writeJson(HttpServletResponse resp, Result<?> result, int status) throws IOException {
        resp.setStatus(status);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(this.objectMapper.writeValueAsString(result));
    }

    public SecurityConfig(JwtAuthFilter jwtAuthFilter, LoginDecryptFilter loginDecryptFilter, ObjectMapper objectMapper) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.loginDecryptFilter = loginDecryptFilter;
        this.objectMapper = objectMapper;
    }
}

