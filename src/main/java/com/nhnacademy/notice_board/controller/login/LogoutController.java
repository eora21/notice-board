package com.nhnacademy.notice_board.controller.login;

import com.nhnacademy.notice_board.anotation.RequestMapping;
import com.nhnacademy.notice_board.controller.Command;
import com.nhnacademy.notice_board.exception.NotFoundException;
import com.nhnacademy.notice_board.servlet.FrontServlet;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Slf4j
@RequestMapping(url = "/logout.do")
public class LogoutController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);

        if (session != null) {
            session.invalidate();

            Cookie cookie = Arrays.stream(req.getCookies())
                    .filter(c -> c.getName().equals("JSESSIONID"))
                    .findAny()
                    .orElseThrow(NotFoundException::new);

            cookie.setValue("");
            cookie.setMaxAge(0);
            resp.addCookie(cookie);
        }

        return FrontServlet.REDIRECT + "/login.jsp";
    }
}
