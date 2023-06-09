package com.nhnacademy.notice_board.filter;

import com.nhnacademy.notice_board.model.post.Post;
import com.nhnacademy.notice_board.repository.post.PostRepository;
import com.nhnacademy.notice_board.servlet.FrontServlet;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@WebFilter(filterName = "postViewFilter", urlPatterns = "/post/view.do")
public class PostViewFilter implements Filter {

    private static final PostRepository POST_REPOSITORY = FrontServlet.POST_REPOSITORY;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String id = httpServletRequest.getParameter("id");
        Post post = POST_REPOSITORY.getPost(Long.parseLong(id));
        post.increaseViewCount();

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
