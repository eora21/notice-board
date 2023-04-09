package com.nhnacademy.notice_board.controller.user;

import com.nhnacademy.notice_board.anotation.RequestMapping;
import com.nhnacademy.notice_board.controller.Command;
import com.nhnacademy.notice_board.exception.FileUploadException;
import com.nhnacademy.notice_board.model.user.Authority;
import com.nhnacademy.notice_board.model.user.User;
import com.nhnacademy.notice_board.repository.user.MemoryUserRepository;
import com.nhnacademy.notice_board.repository.user.UserRepository;
import com.nhnacademy.notice_board.servlet.FrontServlet;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@Slf4j
@RequestMapping(url = "/user/add.do", method = RequestMapping.Method.POST)
public class UserAddController implements Command {
    private static final String UPLOAD_DIR = "/Users/eora21/Desktop/nhn/projects/notice_board/upload";
    private final UserRepository userRepository = MemoryUserRepository.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        String pw = req.getParameter("pw");
        String name = req.getParameter("name");
        log.info("유저 추가: {}, {}, {}", id, pw, name);
        User user = new User(id, pw, name, "", Authority.USER);
        userRepository.add(user);

        try {
            Part profile = req.getPart("profile");

            if (profile.getSize() > 0) {
                String fileName = profile.getSubmittedFileName();
                String extension = fileName.substring(fileName.lastIndexOf('.'));

                String profilePath = UPLOAD_DIR + File.separator + id + extension;

                profile.write(profilePath);
                user.updateProfile(profilePath);
            }

            profile.delete();
            return FrontServlet.REDIRECT + "/user/view.do?id=" + id;

        } catch (IOException | ServletException e) {
            throw new FileUploadException();
        }



    }
}
