package com.nhnacademy.notice_board.init;

import com.nhnacademy.notice_board.controller.Command;
import com.nhnacademy.notice_board.model.user.Authority;
import com.nhnacademy.notice_board.model.user.User;
import com.nhnacademy.notice_board.repository.user.MemoryUserRepository;
import com.nhnacademy.notice_board.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.util.Set;

@Slf4j
@HandlesTypes(value = {
        Command.class
})
public class AppInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        UserRepository userRepository = MemoryUserRepository.getInstance();

        userRepository.add(new User("admin", "12345", "관리자", "", Authority.ADMIN));

        ControllerFactory controllerFactory = new ControllerFactory();
        controllerFactory.init(set);
        servletContext.setAttribute("controllerFactory", controllerFactory);
    }
}
