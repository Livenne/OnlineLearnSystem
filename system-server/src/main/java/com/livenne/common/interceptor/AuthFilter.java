package com.livenne.common.interceptor;

import com.livenne.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
@WebFilter("/*")
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI();

        if ("OPTIONS".equals(req.getMethod())) {
            chain.doFilter(request, response);
            return;
        }

        if (uri.startsWith("/auth")){
            chain.doFilter(request, response);
            return;
        }

        String token = req.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            return;
        }

        token = token.substring("Bearer ".length());

        if (!JwtUtils.validateToken(token)){
            return;
        }

        req.setAttribute("userId", Long.parseLong(JwtUtils.getDecoded(token).getIssuer()));

        chain.doFilter(request,response);
    }
}
