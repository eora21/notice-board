package com.nhnacademy.notice_board.controller.post;

import com.nhnacademy.notice_board.anotation.RequestMapping;
import com.nhnacademy.notice_board.controller.Command;
import com.nhnacademy.notice_board.repository.post.PostRepository;
import com.nhnacademy.notice_board.servlet.FrontServlet;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequestMapping(url = "/post/update.do")
public class PostUpdateFormController implements Command {
    private static final PostRepository POST_REPOSITORY = FrontServlet.POST_REPOSITORY;

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        long id = Long.parseLong(req.getParameter("id"));
        req.setAttribute("post", POST_REPOSITORY.getPost(id));
        return "/post/write.jsp";
    }
}
