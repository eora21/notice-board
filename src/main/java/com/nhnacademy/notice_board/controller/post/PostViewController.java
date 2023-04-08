package com.nhnacademy.notice_board.controller.post;

import com.nhnacademy.notice_board.anotation.RequestMapping;
import com.nhnacademy.notice_board.controller.Command;
import com.nhnacademy.notice_board.model.post.Post;
import com.nhnacademy.notice_board.model.user.User;
import com.nhnacademy.notice_board.repository.post.MemoryPostRepository;
import com.nhnacademy.notice_board.repository.post.PostRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequestMapping(url = "/post/view.do")
public class PostViewController implements Command {
    private final PostRepository postRepository = MemoryPostRepository.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        Post post = postRepository.getPost(Long.parseLong(id));
        req.setAttribute("post", post);
        return "/post/view.jsp";
    }
}
