package com.nhnacademy.notice_board.controller.post;

import com.nhnacademy.notice_board.anotation.RequestMapping;
import com.nhnacademy.notice_board.controller.Command;
import com.nhnacademy.notice_board.exception.NotHavePermissionException;
import com.nhnacademy.notice_board.model.post.Post;
import com.nhnacademy.notice_board.model.user.Authority;
import com.nhnacademy.notice_board.model.user.User;
import com.nhnacademy.notice_board.repository.post.PostRepository;
import com.nhnacademy.notice_board.servlet.FrontServlet;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequestMapping(url = "/post/update.do", method = RequestMapping.Method.POST)
public class PostUpdateController implements Command {
    private static final PostRepository POST_REPOSITORY = FrontServlet.POST_REPOSITORY;

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        long id = Long.parseLong(req.getParameter("id"));
        Post post = POST_REPOSITORY.getPost(id);
        String writerUserId = post.getWriterUserId();
        String userId = (String) req.getSession(false).getAttribute("userId");

        if (!writerUserId.equals(userId)) {
            throw new NotHavePermissionException();
        }

        String title = req.getParameter("title");
        String content = req.getParameter("content");

        post.update(title, content);
        POST_REPOSITORY.modify(post);
        return FrontServlet.REDIRECT + "/post/view.do?id=" + id;
    }
}
