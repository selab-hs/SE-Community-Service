package com.hs.selab.comment.infrastructure;

import com.hs.selab.comment.domain.Comment;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostId(Long postId);

    boolean existsByIdAndMemberId(Long id, Long memberId);
}
