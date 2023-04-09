package com.nhnacademy.notice_board.repository.user;

import com.nhnacademy.notice_board.model.user.User;

import java.util.List;

public interface UserRepository {
    void add(User user);

    void modify(User user);

    User remove(String id);

    User getUser(String id);

    List<User> getUsers();

    boolean exist(String id);
}
