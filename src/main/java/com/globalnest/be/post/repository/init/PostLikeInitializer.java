package com.globalnest.be.post.repository.init;

import com.globalnest.be.global.util.DummyDataInit;
import com.globalnest.be.post.domain.Post;
import com.globalnest.be.post.domain.PostLike;
import com.globalnest.be.post.repository.PostLikeRepository;
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
public class PostLikeInitializer implements ApplicationRunner {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (postLikeRepository.count() > 0) {
            log.info("[PostLike]더미 데이터 존재");
        } else {
            User DUMMY_USER1 = userRepository.findById(1L).orElseThrow();
            Post DUMMY_POST1 = postRepository.findById(1L).orElseThrow();

            List<PostLike> postTagList = new ArrayList<>();

            PostLike postTag1 = PostLike.builder()
                    .user(DUMMY_USER1)
                    .post(DUMMY_POST1)
                    .build();

            postTagList.add(postTag1);

            postLikeRepository.saveAll(postTagList);
        }
    }
}
