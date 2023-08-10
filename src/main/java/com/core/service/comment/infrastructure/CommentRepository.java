package com.core.service.comment.infrastructure;

import com.core.service.comment.domain.Comment;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByIdAndBoardId(Long id, Long boardId);

    List<Comment> findByIdIn(List<Long> ids);

    void deleteByIdAndBoardId(Long commentId, Long boardId);
}