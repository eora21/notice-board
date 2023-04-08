package com.nhnacademy.notice_board.repository.post;

import com.nhnacademy.notice_board.exception.AlreadyExistException;
import com.nhnacademy.notice_board.exception.NotFoundException;
import com.nhnacademy.notice_board.model.post.Post;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemoryPostRepository implements PostRepository {
    private static final Map<Long, Post> POSTS = new TreeMap<>(Collections.reverseOrder());
    private static final MemoryPostRepository MEMORY_POST_REPOSITORY = new MemoryPostRepository();

    public static MemoryPostRepository getInstance() {
        return MEMORY_POST_REPOSITORY;
    }

    @Override
    public long register(Post post) {
        validIdNotExist(post.getId());
        POSTS.put(post.getId(), post);
        return post.getId();
    }

    @Override
    public void modify(Post post) {
        getPost(post.getId()).update(post);
    }

    @Override
    public Post remove(long id) {
        validIdExist(id);
        return POSTS.remove(id);
    }

    @Override
    public Post getPost(long id) {
        validIdExist(id);
        return POSTS.get(id);
    }

    @Override
    public List<Post> getPosts() {
        return new ArrayList<>(POSTS.values());
    }

    private static void validIdExist(long id) {
        if (!POSTS.containsKey(id)) {
            throw new NotFoundException();
        }
    }

    private static void validIdNotExist(long id) {
        if (POSTS.containsKey(id)) {
            throw new AlreadyExistException();
        }
    }
}
