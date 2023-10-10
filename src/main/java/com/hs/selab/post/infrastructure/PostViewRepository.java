package com.hs.selab.post.infrastructure;


import com.hs.selab.post.domain.PostView;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostViewRepository extends JpaRepository<PostView, Long> {
    Optional<PostView> findByPostId(Long postId);
}
