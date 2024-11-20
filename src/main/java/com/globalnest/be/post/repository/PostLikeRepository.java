package com.globalnest.be.post.repository;

import com.globalnest.be.post.domain.Post;
import com.globalnest.be.post.domain.PostLike;
import com.globalnest.be.user.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    Optional<PostLike> findByPostAndUser(Post post, User user);
}
