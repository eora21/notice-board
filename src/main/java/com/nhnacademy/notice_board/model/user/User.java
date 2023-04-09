package com.nhnacademy.notice_board.model.user;

import com.nhnacademy.notice_board.exception.NotEqualIdException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class User {
    private final String id;
    private String password;
    private String name;
    private String profileFileName;
    private Authority authority;

    public void update(String password, String name) throws NotEqualIdException {
        this.password = password;
        this.name = name;
    }

    public void updateProfile(String profileFileName) {
        this.profileFileName = profileFileName;
    }

    public boolean isCorrectPassword(String password) {
        return this.password.equals(password);
    }
}

