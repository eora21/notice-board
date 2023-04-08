package com.nhnacademy.notice_board.controller.post;

import com.nhnacademy.notice_board.anotation.RequestMapping;
import com.nhnacademy.notice_board.controller.Command;
import com.nhnacademy.notice_board.repository.post.MemoryPostRepository;
import com.nhnacademy.notice_board.repository.post.PostRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(url = "/post/list.do")
public class PostListController implements Command {

    private final PostRepository memoryPostRepository = MemoryPostRepository.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("posts", memoryPostRepository.getPosts());

        return "/post/list.jsp";
    }
}
