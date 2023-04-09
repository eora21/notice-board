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

    public void updateProfile(User user) throws NotEqualIdException {
        if (!this.id.equals(user.getId())) {
            throw new NotEqualIdException();
        }

        this.password = user.getPassword();
        this.name = user.getName();
        this.profileFileName = user.getProfileFileName();
        this.authority = user.getAuthority();
    }

    public void updateProfile(String profileFileName) {
        this.profileFileName = profileFileName;
    }

    public boolean isCorrectPassword(String password) {
        return this.password.equals(password);
    }
}

