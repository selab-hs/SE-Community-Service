package com.core.service.comment.application;

import static com.core.service.error.dto.ErrorMessage.UNAUTHORIZED_ACCESS_EXCEPTION;

import com.core.service.auth.domain.UserDetail;
import com.core.service.comment.domain.converter.CommentConverter;
import com.core.service.comment.dto.request.CreateCommentRequest;
import com.core.service.comment.dto.request.ReadCommentRequest;
import com.core.service.comment.dto.request.UpdateCommentRequest;
import com.core.service.comment.dto.response.ReadCommentResponse;
import com.core.service.comment.infrastructure.CommentRepository;

import com.core.service.error.exception.member.UnauthorizedAccessException;
import com.core.service.member.domain.vo.RoleType;
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
    public void create(CreateCommentRequest request, UserDetail userInfo) {
        if (!userInfo.getRoleType().equals(RoleType.LAB_USER)) {
            throw new UnauthorizedAccessException(
                UNAUTHORIZED_ACCESS_EXCEPTION,
                "권한이 없는 접근입니다."
            );
        }
        commentRepository.save(
            converter.convertToEntity(request, userInfo)
        );
    }

    @Transactional
    public void update(Long commentId, UpdateCommentRequest request, UserDetail userInfo) {
        if (!commentRepository.existsByIdAndMemberId(commentId, userInfo.getId())) {
            throw new UnauthorizedAccessException(
                UNAUTHORIZED_ACCESS_EXCEPTION,
                "권한이 없는 접근입니다."
            );
        }
        var comment = commentRepository.findById(commentId)
            .orElseThrow(() -> new IllegalStateException("해당 댓글이 존재하지 않습니다."));
        comment.update(request);
    }

    @Transactional(readOnly = true)
    public List<ReadCommentResponse> getAll(ReadCommentRequest request, UserDetail userInfo) {
        if (!userInfo.getRoleType().equals(RoleType.LAB_USER)) {
            throw new UnauthorizedAccessException(
                UNAUTHORIZED_ACCESS_EXCEPTION,
                "권한이 없는 접근입니다."
            );
        }

        return converter.convertToReadCommentResponse(
            commentRepository.findAllByBoardId(request.getBoardId())
        );
    }

    @Transactional
    public void delete(Long commentId, UserDetail userInfo) {
        if (!commentRepository.existsByIdAndMemberId(commentId, userInfo.getId())) {
            throw new UnauthorizedAccessException(
                UNAUTHORIZED_ACCESS_EXCEPTION,
                "권한이 없는 접근입니다."
            );
        }
        commentRepository.deleteById(commentId);
    }
}