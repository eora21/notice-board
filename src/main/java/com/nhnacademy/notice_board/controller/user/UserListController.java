package com.nhnacademy.notice_board.controller.user;

import com.nhnacademy.notice_board.anotation.RequestMapping;
import com.nhnacademy.notice_board.controller.Command;
import com.nhnacademy.notice_board.repository.user.UserRepository;
import com.nhnacademy.notice_board.servlet.FrontServlet;
import com.nhnacademy.notice_board.util.LoginUserCounterUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(url = "/user/list.do")
public class UserListController implements Command {

    private static final UserRepository USER_REPOSITORY = FrontServlet.USER_REPOSITORY;

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("users", USER_REPOSITORY.getUsers());
        req.setAttribute("loginCount", LoginUserCounterUtil.getCount());

        return "/user/list.jsp";
    }
}
