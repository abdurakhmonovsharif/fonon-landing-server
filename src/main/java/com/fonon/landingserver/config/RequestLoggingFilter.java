package com.fonon.landingserver.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class RequestLoggingFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(RequestLoggingFilter.class);
    private static final Set<String> SKIP_PREFIXES = Set.of(
            "/storage/",
            "/swagger-ui",
            "/docs",
            "/v3/api-docs",
            "/actuator"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        long start = System.currentTimeMillis();
        String requestId = UUID.randomUUID().toString().substring(0, 8);
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String query = request.getQueryString();
        String fullPath = query == null ? uri : uri + "?" + query;

        log.info("[{}] Incoming {} {} from {}", requestId, method, fullPath, request.getRemoteAddr());
        boolean threwException = false;
        Exception captured = null;
        try {
            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            threwException = true;
            captured = ex;
            log.error("[{}] Exception processing {} {}: {}", requestId, method, fullPath, ex.getMessage(), ex);
            throw ex;
        } finally {
            long duration = System.currentTimeMillis() - start;
            int status = response.getStatus();
            if (status == 0 && threwException) {
                status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
            }
            String statusPhrase = resolveReasonPhrase(status);
            String message = String.format("[%s] Completed %s %s -> %d %s (%d ms)",
                    requestId, method, uri, status, statusPhrase, duration);

            if (threwException || status >= 500) {
                log.error(message, captured);
            } else if (status >= 400) {
                log.warn(message);
            } else {
                log.info(message);
            }
        }
    }

    private String resolveReasonPhrase(int status) {
        try {
            return HttpStatus.valueOf(status).getReasonPhrase();
        } catch (Exception ex) {
            return "";
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String uri = request.getRequestURI();
        if (uri == null) {
            return false;
        }
        return SKIP_PREFIXES.stream().anyMatch(uri::startsWith);
    }
}
