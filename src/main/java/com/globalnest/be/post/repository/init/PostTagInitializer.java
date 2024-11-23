package com.globalnest.be.post.repository.init;

import com.globalnest.be.global.util.DummyDataInit;
import com.globalnest.be.post.domain.Post;
import com.globalnest.be.post.domain.PostTag;
import com.globalnest.be.post.repository.PostRepository;
import com.globalnest.be.post.repository.PostTagRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
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

    private final Random random = new Random();
    private final String[] tags = {"ANGRY", "HAPPY", "SAD", "EXCITED", "BORED", "SURPRISED"};

    @Override
    public void run(ApplicationArguments args) {
        if (postTagRepository.count() > 0) {
            log.info("[PostTag] 더미 데이터 존재");
        } else {
            List<PostTag> postTagList = new ArrayList<>();

            for (long postId = 1; postId <= 24; postId++) {
                Post post = postRepository.findById(postId).orElseThrow();

                Set<String> usedTags = new HashSet<>(); // 중복 태그 방지를 위한 Set

                // 랜덤하게 태그를 선택하여 생성
                while (usedTags.size() < 3) { // 각 포스트에 3개의 태그를 달기
                    String randomTag = tags[random.nextInt(tags.length)];
                    if (!usedTags.contains(randomTag)) { // 중복 체크
                        PostTag postTag = PostTag.builder()
                                .post(post)
                                .tag(randomTag)
                                .build();
                        postTagList.add(postTag);
                        usedTags.add(randomTag); // 사용한 태그 추가
                    }
                }
            }

            postTagRepository.saveAll(postTagList);
            log.info("[PostTag] 더미 데이터 초기화 완료");
        }
    }
}
