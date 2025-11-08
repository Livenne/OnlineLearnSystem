package com.livenne.common.interceptorr;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@WebFilter("/*")
public class RouterFilter implements Filter {

    private final Set<String> whitelist = new HashSet<>() {
        {
//            add("/user/login");
//            add("/user/register");
//            add("/user/verify");
//            add("/image");
        }
    };

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        req.setCharacterEncoding("UTF-8");
        res.setContentType("application/json;charset=UTF-8");
        res.setCharacterEncoding("UTF-8");

        String path = req.getRequestURI();
//        if (req.getMethod().equals("OPTIONS")) {
//            chain.doFilter(request, response);
//            return;
//        }

        System.out.println("path:"+path);

//        for (String s : whitelist) {
//            if (path.startsWith(s)) {
//                chain.doFilter(request, response);
//                return;
//            }
//        }
//        String token = req.getHeader("Authorization");
//        if (token == null) return;
//        if (!token.startsWith("Bearer ")) return;
//        token = token.substring("Bearer ".length());
//
//        if (!JwtUtils.validateToken(token)) return;
//        Long userId = Long.valueOf(JwtUtils.getDecoded(token).getIssuer());
//        req.setAttribute("userId", userId);
        chain.doFilter(request, response);
    }
}
