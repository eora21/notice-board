package com.nhnacademy.notice_board.servlet;

import com.nhnacademy.notice_board.controller.Command;
import com.nhnacademy.notice_board.exception.NotFoundException;
import com.nhnacademy.notice_board.init.ControllerFactory;
import com.nhnacademy.notice_board.model.user.Authority;
import com.nhnacademy.notice_board.repository.post.MemoryPostRepository;
import com.nhnacademy.notice_board.repository.post.PostRepository;
import com.nhnacademy.notice_board.repository.user.MemoryUserRepository;
import com.nhnacademy.notice_board.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@WebServlet(name = "frontServlet", urlPatterns = "*.do")
@MultipartConfig(
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
        // 상대경로 미지원, 없을 시 알아서 임시폴더 경로 작성
)
public class FrontServlet extends HttpServlet {
    public static final String REDIRECT = "redirect:";
    public static final UserRepository USER_REPOSITORY = MemoryUserRepository.getInstance();
    public static final PostRepository POST_REPOSITORY = MemoryPostRepository.getInstance();
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
