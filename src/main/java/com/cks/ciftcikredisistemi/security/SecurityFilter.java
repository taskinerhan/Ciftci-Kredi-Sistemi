package com.cks.ciftcikredisistemi.security;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    private static final String SECURE_KEY_HEADER  = "x-secure-key";
    private static final String SECURE_KEY_HEADER_VALUE   = "6RcVRuwxUr07F54B7a9IPomjh";
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String secureKey = request.getHeader(SECURE_KEY_HEADER);
        if (StringUtils.isEmpty(secureKey) || !secureKey.equals(SECURE_KEY_HEADER_VALUE)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }
        filterChain.doFilter(request, response);
    }
}