package com.nhnacademy.notice_board.listener;

import com.nhnacademy.notice_board.util.LoginUserCounterUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@WebListener
public class HttpSessionListenerImpl implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        LoginUserCounterUtil.increase();
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        LoginUserCounterUtil.decrease();
    }
}
