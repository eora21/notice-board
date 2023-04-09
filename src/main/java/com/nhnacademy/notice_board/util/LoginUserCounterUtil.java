package com.nhnacademy.notice_board.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicInteger;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginUserCounterUtil {

    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(0);

    public static void increase() {
        ATOMIC_INTEGER.incrementAndGet();
    }

    public static void decrease() {
        ATOMIC_INTEGER.decrementAndGet();
    }

    public static int getCount() {
        return ATOMIC_INTEGER.get();
    }
}
