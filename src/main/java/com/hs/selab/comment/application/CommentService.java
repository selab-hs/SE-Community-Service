package com.hs.selab.comment.application;

import static com.hs.selab.error.dto.ErrorMessage.UNAUTHORIZED_ACCESS_EXCEPTION;

import com.hs.selab.auth.domain.UserDetail;
import com.hs.selab.comment.domain.converter.CommentConverter;
import com.hs.selab.comment.dto.request.CreateCommentRequest;
import com.hs.selab.comment.dto.request.ReadCommentRequest;
import com.hs.selab.comment.dto.request.UpdateCommentRequest;
import com.hs.selab.comment.dto.response.ReadCommentResponse;
import com.hs.selab.comment.infrastructure.CommentRepository;

import com.hs.selab.error.exception.member.UnauthorizedAccessException;
import com.hs.selab.member.domain.vo.RoleType;
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
        if (!userInfo.getRoleType().equals(RoleType.USER)) {
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
        if (!userInfo.getRoleType().equals(RoleType.USER)) {
            throw new UnauthorizedAccessException(
                UNAUTHORIZED_ACCESS_EXCEPTION,
                "권한이 없는 접근입니다."
            );
        }

        return converter.convertToReadCommentResponse(
            commentRepository.findAllByPostId(request.getPostId())
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
