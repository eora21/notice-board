package com.nhnacademy.notice_board.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Authority {
    ADMIN("/user/list.do"),
    USER("/post/list.do");

    private final String defaultUrl;
}
