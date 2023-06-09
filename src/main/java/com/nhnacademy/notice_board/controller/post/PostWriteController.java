package com.nhnacademy.notice_board.controller.post;

import com.nhnacademy.notice_board.anotation.RequestMapping;
import com.nhnacademy.notice_board.controller.Command;
import com.nhnacademy.notice_board.model.post.Post;
import com.nhnacademy.notice_board.repository.post.MemoryPostRepository;
import com.nhnacademy.notice_board.repository.post.PostRepository;
import com.nhnacademy.notice_board.repository.user.MemoryUserRepository;
import com.nhnacademy.notice_board.repository.user.UserRepository;
import com.nhnacademy.notice_board.servlet.FrontServlet;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequestMapping(url = "/post/write.do", method = RequestMapping.Method.POST)
public class PostWriteController implements Command {
    private static final PostRepository POST_REPOSITORY = FrontServlet.POST_REPOSITORY;

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String writerUserId = (String) req.getSession(false).getAttribute("userId");

        long id = POST_REPOSITORY.register(Post.builder()
                .title(title)
                .content(content)
                .writerUserId(writerUserId)
                .build());

        return FrontServlet.REDIRECT + "/post/view.do?id=" + id;
    }
}
