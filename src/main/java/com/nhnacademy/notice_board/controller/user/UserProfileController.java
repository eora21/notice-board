package com.nhnacademy.notice_board.controller.user;

import com.nhnacademy.notice_board.anotation.RequestMapping;
import com.nhnacademy.notice_board.controller.Command;
import com.nhnacademy.notice_board.exception.FileUploadException;
import com.nhnacademy.notice_board.model.user.User;
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
@RequestMapping(url = "/user/profile.do", method = RequestMapping.Method.POST)
public class UserProfileController implements Command {
    private static final String UPLOAD_DIR = "/upload";
    private static final UserRepository USER_REPOSITORY = FrontServlet.USER_REPOSITORY;

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String id = req.getParameter("id");
            User user = USER_REPOSITORY.getUser(id);

            String profileFileName = user.getProfileFileName();
            File file = new File(profileFileName);

            if (profileFileName.length() > 0 && file.exists()) {
                file.delete();
            }

            Part profile = req.getPart("profile");

            if (profile.getSize() <= 0) {
                throw new FileUploadException();
            }

            String fileName = profile.getSubmittedFileName();
            String extension = fileName.substring(fileName.lastIndexOf('.'));

            String profilePath = UPLOAD_DIR + File.separator + id + extension;

            profile.write(req.getServletContext().getRealPath(profilePath));
            user.updateProfile(profilePath);

            profile.delete();
            return FrontServlet.REDIRECT + "/user/view.do?id=" + id;

        } catch (IOException | ServletException e) {
            throw new FileUploadException();
        }
    }
}
