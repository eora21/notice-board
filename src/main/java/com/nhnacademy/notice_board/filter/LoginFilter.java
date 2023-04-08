package com.nhnacademy.notice_board.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Slf4j
@WebFilter(filterName = "loginFilter", urlPatterns = "/*")
public class LoginFilter implements Filter {

    private static final Set<String> EXCLUSIVE_URL =
            Set.of("/login.do", "/logout.do", "/login/login.jsp", "/error.jsp");

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        log.info("loginFilter: {}", httpServletRequest.getServletPath());

        if (httpServletRequest.getSession(false) == null
                && (!EXCLUSIVE_URL.contains(httpServletRequest.getServletPath()))) {
            ((HttpServletResponse) servletResponse).sendRedirect("/login/login.jsp");
            return;

        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
