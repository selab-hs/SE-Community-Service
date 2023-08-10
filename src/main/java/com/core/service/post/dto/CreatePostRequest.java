package com.core.service.post.dto;

import lombok.Getter;

@Getter
public class CreatePostRequest {
    Long boardId;
    Long commentId;

    public CreatePostRequest(Long boardId, Long commentId) {
        this.boardId = boardId;
        this.commentId = commentId;
    }
}