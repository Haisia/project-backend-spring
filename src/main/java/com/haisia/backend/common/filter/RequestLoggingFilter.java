package com.haisia.backend.common.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class RequestLoggingFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;

    String method = httpRequest.getMethod();
    String uri = httpRequest.getRequestURI();
    String queryString = httpRequest.getQueryString();
    String clientIp = request.getRemoteAddr();

    long startTime = System.currentTimeMillis();

    if (queryString != null) {
      log.info("[REQUEST] {} {}?{} - Client IP: {}", method, uri, queryString, clientIp);
    } else {
      log.info("[REQUEST] {} {} - Client IP: {}", method, uri, clientIp);
    }

    try {
      chain.doFilter(request, response);
    } finally {
      long endTime = System.currentTimeMillis();
      long duration = endTime - startTime;
      log.info("[RESPONSE] {} {} - {}ms", method, uri, duration);
    }
  }
}
