package com.nhnacademy.notice_board.controller.post;

import com.nhnacademy.notice_board.anotation.RequestMapping;
import com.nhnacademy.notice_board.controller.Command;
import com.nhnacademy.notice_board.exception.NotHavePermissionException;
import com.nhnacademy.notice_board.model.user.Authority;
import com.nhnacademy.notice_board.repository.user.MemoryUserRepository;
import com.nhnacademy.notice_board.repository.user.UserRepository;
import com.nhnacademy.notice_board.servlet.FrontServlet;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequestMapping(url = "/user/delete.do", method = RequestMapping.Method.POST)
public class UserDeleteController implements Command {
    private final UserRepository userRepository = MemoryUserRepository.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        Authority userAuthority = userRepository.getUser(id).getAuthority();
        Authority myAuthority = (Authority) req.getSession().getAttribute("authority");

        if (userAuthority == Authority.ADMIN || myAuthority != Authority.ADMIN) {
            throw new NotHavePermissionException();
        }

        userRepository.remove(id);
        return FrontServlet.REDIRECT + "/user/list.do";
    }
}
