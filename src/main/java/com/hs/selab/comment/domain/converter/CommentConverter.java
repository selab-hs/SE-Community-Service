package com.hs.selab.comment.domain.converter;

import com.hs.selab.auth.domain.UserDetail;
import com.hs.selab.comment.domain.Comment;
import com.hs.selab.comment.dto.request.CreateCommentRequest;
import com.hs.selab.comment.dto.response.ReadCommentResponse;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class CommentConverter {


    public Comment convertToEntity(CreateCommentRequest request, UserDetail userInfo) {
        return Comment.builder()
                .memberId(userInfo.getId())
                .postId(request.getPostId())
                .comment(request.getComment())
                .build();
    }

    public List<ReadCommentResponse> convertToReadCommentResponse(List<Comment> comments) {
        return comments.stream().map(
                comment -> ReadCommentResponse.builder()
                        .commentId(comment.getId())
                        .memberId(comment.getMemberId())
                        .postId(comment.getPostId())
                        .comment(comment.getComment())
                        .build()
        ).collect(Collectors.toList());
    }
}
