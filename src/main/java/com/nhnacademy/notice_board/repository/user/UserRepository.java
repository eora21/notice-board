package com.nhnacademy.notice_board.repository.user;

import com.nhnacademy.notice_board.exception.NotEqualIdException;
import com.nhnacademy.notice_board.exception.AlreadyExistException;
import com.nhnacademy.notice_board.exception.NotFoundException;
import com.nhnacademy.notice_board.model.user.User;

import java.util.List;

public interface UserRepository {
    void add(User user) throws AlreadyExistException;
    void modify(User user) throws NotFoundException, NotEqualIdException;
    User remove(String id);

    User getUser(String id) throws NotFoundException;
    List<User> getUsers();
}
