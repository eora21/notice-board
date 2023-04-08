package com.nhnacademy.notice_board.model.post;

import com.nhnacademy.notice_board.exception.NotEqualIdException;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

@Getter
public class Post {
    private static final AtomicLong AUTO_INCREMENT = new AtomicLong(1);

    private final long id;
    private String title;
    private String content;
    private final String writerUserId;
    private final LocalDateTime writeTime;
    private int viewCount;

    @Builder
    private Post(String title, String content, String writerUserId) {
        this.id = AUTO_INCREMENT.getAndIncrement();
        this.title = title;
        this.content = content;
        this.writerUserId = writerUserId;
        this.writeTime = LocalDateTime.now();
        this.viewCount = 0;
    }

    public void update(Post post) throws NotEqualIdException {
        if (this.id != post.getId()) {
            throw new NotEqualIdException();
        }
        this.title = post.getTitle();
        this.content = post.getContent();
    }

    public void increaseViewCount() {
        viewCount++;
    }
}
