package com.core.service.comment.infrastructure;

import com.core.service.comment.domain.Comment;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByBoardId(Long boardId);

    boolean existsByIdAndMemberId(Long id, Long memberId);
}