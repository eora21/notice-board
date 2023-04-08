package com.nhnacademy.notice_board.filter;

import com.nhnacademy.notice_board.model.user.Authority;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@WebFilter(filterName = "userPageFilter", urlPatterns = "/user/*")
public class UserPageFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        log.info("userPageFilter");

        HttpSession session = httpServletRequest.getSession(false);
        if (session == null) {
            httpServletResponse.sendRedirect("/login/login.jsp");
            return;
        }
        Authority authority = (Authority) session.getAttribute("authority");
        if (authority != Authority.ADMIN) {
            httpServletResponse.sendRedirect(authority.getDefaultUrl());
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
