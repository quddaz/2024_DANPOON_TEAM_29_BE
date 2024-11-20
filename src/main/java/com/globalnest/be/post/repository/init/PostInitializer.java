package com.globalnest.be.post.repository.init;

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
@Order(2)
@DummyDataInit
public class PostInitializer implements ApplicationRunner {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (postRepository.count() > 0) {
            log.info("[Post]더미 데이터 존재");
        } else {
            User DUMMY_ADMIN = userRepository.findById(1L).orElseThrow();
            User DUMMY_USER1 = userRepository.findById(2L).orElseThrow();
            User DUMMY_USER2 = userRepository.findById(3L).orElseThrow();

            List<Post> postList = new ArrayList<>();

            Post DUMMY_POST1 = Post.builder()
                    .user(DUMMY_ADMIN)
                    .title("제목1")
                    .content("내용1")
                    .build();
            Post DUMMY_POST2 = Post.builder()
                    .user(DUMMY_USER1)
                    .title("제목2")
                    .content("내용2")
                    .build();
            Post DUMMY_POST3 = Post.builder()
                    .user(DUMMY_USER2)
                    .title("제목3")
                    .content("내용3")
                    .build();
            Post DUMMY_POST4 = Post.builder()
                    .user(DUMMY_USER2)
                    .title("제목4")
                    .content("내용4")
                    .build();

            postList.add(DUMMY_POST1);
            postList.add(DUMMY_POST2);
            postList.add(DUMMY_POST3);
            postList.add(DUMMY_POST4);

            postRepository.saveAll(postList);
        }
    }
}
