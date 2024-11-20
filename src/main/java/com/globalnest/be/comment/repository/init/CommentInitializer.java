package com.globalnest.be.comment.repository.init;

import com.globalnest.be.comment.domain.Comment;
import com.globalnest.be.comment.repository.CommentRepository;
import com.globalnest.be.global.util.DummyDataInit;
import com.globalnest.be.post.domain.Post;
import com.globalnest.be.post.repository.PostRepository;
import com.globalnest.be.user.domain.User;
import com.globalnest.be.user.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;

@Slf4j
@RequiredArgsConstructor
@Order(3)
@DummyDataInit
public class CommentInitializer implements ApplicationRunner {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (commentRepository.count() > 0) {
            log.info("[Comment]더미 데이터 존재");
        } else {
            User DUMMY_ADMIN = userRepository.findById(1L).orElseThrow();
            User DUMMY_USER1 = userRepository.findById(2L).orElseThrow();
            User DUMMY_USER2 = userRepository.findById(3L).orElseThrow();

            Post DUMMY_POST1 = postRepository.findById(1L).orElseThrow();

            List<Comment> commentList = new ArrayList<>();

            Comment DUMMY_COMMENT1 = Comment.builder()
                    .user(DUMMY_ADMIN)
                    .post(DUMMY_POST1)
                    .content("댓글1")
                    .build();
            Comment DUMMY_COMMENT2 = Comment.builder()
                    .user(DUMMY_USER1)
                    .post(DUMMY_POST1)
                    .content("댓글2")
                    .build();
            Comment DUMMY_COMMENT3 = Comment.builder()
                    .user(DUMMY_USER2)
                    .post(DUMMY_POST1)
                    .content("댓글3")
                    .build();
            Comment DUMMY_COMMENT4 = Comment.builder()
                    .user(DUMMY_USER2)
                    .post(DUMMY_POST1)
                    .content("댓글4")
                    .build();

            commentList.add(DUMMY_COMMENT1);
            commentList.add(DUMMY_COMMENT2);
            commentList.add(DUMMY_COMMENT3);
            commentList.add(DUMMY_COMMENT4);

            commentRepository.saveAll(commentList);
        }
    }
}
