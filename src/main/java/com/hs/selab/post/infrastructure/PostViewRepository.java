package com.hs.selab.post.infrastructure;


import com.hs.selab.post.domain.PostView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostViewRepository extends JpaRepository<PostView, Long> {
    Optional<PostView> findByPostId(Long postId);
}
