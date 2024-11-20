package com.globalnest.be.post.repository.init;

import com.globalnest.be.global.util.DummyDataInit;
import com.globalnest.be.post.domain.Post;
import com.globalnest.be.post.domain.PostTag;
import com.globalnest.be.post.repository.PostRepository;
import com.globalnest.be.post.repository.PostTagRepository;
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
public class PostTagInitializer implements ApplicationRunner {

    private final PostTagRepository postTagRepository;
    private final PostRepository postRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (postTagRepository.count() > 0) {
            log.info("[PostTag]더미 데이터 존재");
        } else {
            Post DUMMY_POST1 = postRepository.findById(1L).orElseThrow();

            List<PostTag> postTagList = new ArrayList<>();

            PostTag postTag1 = PostTag.builder()
                    .post(DUMMY_POST1)
                    .tag("ANGRY")
                    .build();
            PostTag postTag2 = PostTag.builder()
                    .post(DUMMY_POST1)
                    .tag("HAPPY")
                    .build();

            postTagList.add(postTag1);
            postTagList.add(postTag2);

            postTagRepository.saveAll(postTagList);
        }
    }
}
