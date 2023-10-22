package com.hs.selab.post.infrastructure;

import com.hs.selab.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {
    boolean existsByIdAndMemberId(Long id, Long memberId);

    @Transactional(readOnly = true)
    Page<Post> findAllByBoardId(Long boardId, Pageable pageable);

    @Transactional(readOnly = true)
    List<Post> findAllByBoardId(Long boardId);

    @Transactional(readOnly = true)
    Page<Post> findAll(Specification<Post> spec, Pageable pageable);

    @Transactional(readOnly = true)
    List<Post> findTop5ByBoardIdOrderByIdDesc(Long boardId);
}
