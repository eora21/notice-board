package com.nhnacademy.notice_board.controller.user;

import com.nhnacademy.notice_board.anotation.RequestMapping;
import com.nhnacademy.notice_board.controller.Command;
import com.nhnacademy.notice_board.model.user.User;
import com.nhnacademy.notice_board.repository.user.UserRepository;
import com.nhnacademy.notice_board.servlet.FrontServlet;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequestMapping(url = "/user/update.do", method = RequestMapping.Method.POST)
public class UserUpdateController implements Command {
    private static final UserRepository USER_REPOSITORY = FrontServlet.USER_REPOSITORY;

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        String pw = req.getParameter("pw");
        String name = req.getParameter("name");
        log.info("유저 업데이트: {}, {}, {}", id, pw, name);
        User user = USER_REPOSITORY.getUser(id);
        user.update(pw, name);
        USER_REPOSITORY.modify(user);

        return FrontServlet.REDIRECT + "/user/view.do?id=" + id;


    }
}
