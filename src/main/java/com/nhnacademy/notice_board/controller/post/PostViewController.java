package com.nhnacademy.notice_board.controller.post;

import com.nhnacademy.notice_board.anotation.RequestMapping;
import com.nhnacademy.notice_board.controller.Command;
import com.nhnacademy.notice_board.model.post.Post;
import com.nhnacademy.notice_board.repository.post.PostRepository;
import com.nhnacademy.notice_board.servlet.FrontServlet;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequestMapping(url = "/post/view.do")
public class PostViewController implements Command {
    private static final PostRepository POST_REPOSITORY = FrontServlet.POST_REPOSITORY;

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        Post post = POST_REPOSITORY.getPost(Long.parseLong(id));
        req.setAttribute("post", post);
        return "/post/view.jsp";
    }
}
