package com.nhnacademy.notice_board.controller.user;

import com.nhnacademy.notice_board.anotation.RequestMapping;
import com.nhnacademy.notice_board.controller.Command;
import com.nhnacademy.notice_board.model.user.Authority;
import com.nhnacademy.notice_board.model.user.User;
import com.nhnacademy.notice_board.repository.user.MemoryUserRepository;
import com.nhnacademy.notice_board.repository.user.UserRepository;
import com.nhnacademy.notice_board.servlet.FrontServlet;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequestMapping(url = "/user/add.do", method = RequestMapping.Method.POST)
public class UserAddController implements Command {
    private final UserRepository userRepository = MemoryUserRepository.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        String pw = req.getParameter("pw");
        String name = req.getParameter("name");

        log.info("유저 추가: {}, {}, {}", id, pw, name);

        userRepository.add(new User(id, pw, name, "", Authority.USER));
        return FrontServlet.REDIRECT + "/user/view.do?id=" + id;
    }
}
