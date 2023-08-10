package com.core.service.post.infrastructure;

import com.core.service.post.domain.Post;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByBoardId(Long boardId);
    void deleteByBoardId(Long boardId);
}