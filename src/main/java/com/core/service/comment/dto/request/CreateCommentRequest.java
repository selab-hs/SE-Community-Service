package com.core.service.comment.dto.request;

import lombok.Getter;

@Getter
public class CreateCommentRequest {
    private Long memberId;
    private Long boardId;
    private String comment;
}
