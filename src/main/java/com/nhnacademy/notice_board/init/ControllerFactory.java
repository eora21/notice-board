package com.nhnacademy.notice_board.init;

import com.nhnacademy.notice_board.anotation.RequestMapping;
import com.nhnacademy.notice_board.controller.Command;
import com.nhnacademy.notice_board.controller.ErrorController;
import com.nhnacademy.notice_board.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Slf4j
public class ControllerFactory {
    private static final Command ERROR_CONTROLLER = new ErrorController();
    private final Map<String, Command> commands = new HashMap<>();


    public void init(Set<Class<?>> classes) {
        for (Class<?> clazz : classes) {
            Optional<Annotation> annotationOptional = Arrays.stream(clazz.getAnnotations())
                    .filter(RequestMapping.class::isInstance)
                    .findAny();

            if (annotationOptional.isEmpty()) {
                continue;
            }

            RequestMapping requestMapping = (RequestMapping) annotationOptional.get();
            RequestMapping.Method[] methods = requestMapping.method();
            String url = requestMapping.url();

            try {
                for (RequestMapping.Method method : methods) {
                    log.info("key: {}, command: {}", method + url, clazz.getName());
                    commands.put(method + url, (Command) clazz.getDeclaredConstructor().newInstance());
                }
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                log.error("COMMANDS init ERROR");
            }


        }
    }

    public Command getCommand(String method, String url) {
        log.info("key: {}", method + url);
        Command command = commands.getOrDefault(method + url, ERROR_CONTROLLER);

//        if (command == null) {
//            throw new NotFoundException();
//        }

        log.info("command: {}", command.getClass().getName());
        return command;
    }

}
