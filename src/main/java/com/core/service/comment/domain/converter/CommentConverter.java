package com.core.service.comment.domain.converter;

import com.core.service.auth.domain.UserDetail;
import com.core.service.comment.domain.Comment;
import com.core.service.comment.dto.request.CreateCommentRequest;
import com.core.service.comment.dto.response.ReadCommentResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter {


    public Comment convertToEntity(CreateCommentRequest request, UserDetail userInfo){
        return Comment.builder()
            .memberId(userInfo.getId())
            .boardId(request.getBoardId())
            .comment(request.getComment())
            .build();
    }

    public List<ReadCommentResponse> convertToReadCommentResponse(List<Comment> comments){
        return comments.stream().map(
          comment -> ReadCommentResponse.builder()
              .commentId(comment.getId())
              .memberId(comment.getMemberId())
              .boardId(comment.getBoardId())
              .comment(comment.getComment())
              .build()
        ).collect(Collectors.toList());
    }
}