package com.core.service.comment.application;

import com.core.service.comment.domain.Comment;
import com.core.service.comment.domain.converter.CommentConverter;
import com.core.service.comment.dto.request.CreateCommentRequest;
import com.core.service.comment.dto.request.UpdateCommentRequest;
import com.core.service.comment.dto.response.ReadCommentResponse;
import com.core.service.comment.infrastructure.CommentRepository;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentConverter converter;

    @Transactional
    public Comment create(CreateCommentRequest request){
        Comment comment =  converter.convertToEntity(request);
        commentRepository.save(comment);

        return comment;
    }

    @Transactional
    public void update(Long id,Long boardId, UpdateCommentRequest request){
        Comment comment = commentRepository.findByIdAndBoardId(id, boardId)
            .orElseThrow(() -> new IllegalStateException("해당 댓글이 존재하지 않습니다."));
        comment.update(request);
        commentRepository.save(comment);
    }

    @Transactional(readOnly = true)
    public List<ReadCommentResponse> getAll(List<Long> commentIds){
        return converter.convertToReadCommentResponse(
            commentRepository.findByIdIn(commentIds)
        );
    }

    @Transactional
    public void delete(Long boardId, Long commentId){
        commentRepository.deleteByIdAndBoardId(boardId, commentId);
    }
}
