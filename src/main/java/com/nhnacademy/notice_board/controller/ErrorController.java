package com.nhnacademy.notice_board.controller;

import com.nhnacademy.notice_board.anotation.RequestMapping;
import com.nhnacademy.notice_board.servlet.FrontServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(url = "/error.do", method = {
        RequestMapping.Method.GET,
        RequestMapping.Method.POST
})
public class ErrorController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return FrontServlet.REDIRECT + "/error.jsp";
    }
}
