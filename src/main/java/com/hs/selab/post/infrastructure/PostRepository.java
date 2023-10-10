package com.hs.selab.post.infrastructure;


import com.hs.selab.post.domain.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByBoardId(Long boardId);
    boolean existsByIdAndMemberId(Long id, Long memberId);
}
