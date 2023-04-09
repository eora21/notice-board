package com.nhnacademy.notice_board.repository.user;

import com.nhnacademy.notice_board.exception.NotEqualIdException;
import com.nhnacademy.notice_board.exception.AlreadyExistException;
import com.nhnacademy.notice_board.exception.NotFoundException;
import com.nhnacademy.notice_board.model.user.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemoryUserRepository implements UserRepository {
    private static final Map<String, User> USERS = new TreeMap<>();
    private static final MemoryUserRepository INSTANCE = new MemoryUserRepository();
    public static MemoryUserRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(User user) throws AlreadyExistException {
        if (exist(user.getId())) {
            throw new AlreadyExistException();
        }
        USERS.put(user.getId(), user);
    }

    @Override
    public void modify(User user) throws NotFoundException, NotEqualIdException {
        if (!exist(user.getId())) {
            throw new AlreadyExistException();
        }
        USERS.put(user.getId(), user);
    }

    @Override
    public User remove(String id) {
        if (!exist(id)) {
            throw new NotFoundException();
        }

        return USERS.remove(id);
    }

    @Override
    public User getUser(String id) throws NotFoundException {
        if (!exist(id)) {
            throw new NotFoundException();
        }

        return USERS.get(id);
    }

    @Override
    public List<User> getUsers() {
        return new ArrayList<>(USERS.values());
    }

    @Override
    public boolean exist(String id) {
        return USERS.containsKey(id);
    }
}
