package com.hs.selab.post.infrastructure;


import com.hs.selab.post.domain.Post;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface PostRepository extends JpaRepository<Post, Long> {
    boolean existsByIdAndMemberId(Long id, Long memberId);

    @Transactional(readOnly = true)
    Page<Post> findAllByBoardId(Long boardId, Pageable pageable);

    @Transactional(readOnly = true)
    List<Post> findAllByBoardId(Long boardId);
}
