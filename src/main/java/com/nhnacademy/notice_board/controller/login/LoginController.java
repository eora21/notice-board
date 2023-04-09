package com.nhnacademy.notice_board.controller.login;

import com.nhnacademy.notice_board.anotation.RequestMapping;
import com.nhnacademy.notice_board.controller.Command;
import com.nhnacademy.notice_board.model.user.User;
import com.nhnacademy.notice_board.repository.user.MemoryUserRepository;
import com.nhnacademy.notice_board.repository.user.UserRepository;
import com.nhnacademy.notice_board.servlet.FrontServlet;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@RequestMapping(url = "/login.do", method = RequestMapping.Method.POST)
public class LoginController implements Command {
    private static final UserRepository USER_REPOSITORY = FrontServlet.USER_REPOSITORY;

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        String pw = req.getParameter("pw");

        User user = USER_REPOSITORY.getUser(id);
        if (user.isCorrectPassword(pw)) {
            HttpSession session = req.getSession(true);
            session.setAttribute("authority", user.getAuthority());
            session.setAttribute("userId", user.getId());
            return FrontServlet.REDIRECT + user.getAuthority().getDefaultUrl();
        }

        return "/login/login.jsp";
    }
}
