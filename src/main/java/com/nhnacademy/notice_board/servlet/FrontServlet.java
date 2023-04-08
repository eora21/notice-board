package com.nhnacademy.notice_board.servlet;

import com.nhnacademy.notice_board.controller.Command;
import com.nhnacademy.notice_board.exception.NotFoundException;
import com.nhnacademy.notice_board.init.ControllerFactory;
import com.nhnacademy.notice_board.model.user.Authority;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@WebServlet(name = "frontServlet", urlPatterns = "*.do")
public class FrontServlet extends HttpServlet {
    public static final String REDIRECT = "redirect:";
    private ControllerFactory controllerFactory;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        controllerFactory = (ControllerFactory) config.getServletContext().getAttribute("controllerFactory");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        String method = req.getMethod();
        String url = req.getServletPath();

        log.info("frontServlet: {}", url);

        try {
            Command command = controllerFactory.getCommand(method, url);
            String view = command.execute(req, resp);

            if (view.startsWith(REDIRECT)) {
                resp.sendRedirect(view.substring(REDIRECT.length()));
                return;
            }

            req.getRequestDispatcher(view).forward(req, resp);
        } catch (NotFoundException e) {
            HttpSession session = req.getSession(false);
            resp.sendRedirect(((Authority) session.getAttribute("authority")).getDefaultUrl());
        } catch (Exception e) {
            log.error(e.toString());
            resp.sendRedirect("/error.jsp");
        }
    }
}
