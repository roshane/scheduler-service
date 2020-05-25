package com.aeon.scheduler.service.util;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

public final class HttpLoggingInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(HttpLoggingInterceptor.class);

    private final String RequestIdHeader = "X-request-id";
    private final String MdcRequestId = "requestId";

    private Map<String, String> getHttpHeaders(final HttpServletRequest request) {
        return Collections.list(request.getHeaderNames()).stream()
                .collect(Collectors.toMap(Function.identity(), request::getHeader, (a, b) -> a));
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        putRequestIdInMdc(request);
        String method = request.getMethod();
        String pathWithQueryString = request.getRequestURI() + getQueryString(request);
        Map<String, String> requestHeaders = getHttpHeaders(request);
        logger.info("Request {} {}\nHeaders {}", method.toUpperCase(), pathWithQueryString, requestHeaders.toString());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        MDC.clear();
    }

    private void putRequestIdInMdc(HttpServletRequest request) {
        Optional.ofNullable(request.getHeader(RequestIdHeader))
                .ifPresent(xRequestId -> MDC.put(MdcRequestId, xRequestId));
    }

    private String getQueryString(HttpServletRequest request) {
        return Optional.ofNullable(request.getQueryString())
                .map(qs -> "?" + qs).orElse("");
    }

}
