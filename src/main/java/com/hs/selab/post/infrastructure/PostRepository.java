package com.hs.selab.post.infrastructure;


import com.hs.selab.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    boolean existsByIdAndMemberId(Long id, Long memberId);
}
