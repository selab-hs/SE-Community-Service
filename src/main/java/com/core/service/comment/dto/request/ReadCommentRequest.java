package com.core.service.comment.dto.request;

import lombok.Getter;

@Getter
public class ReadCommentRequest {
    private final Long boardId;

    public ReadCommentRequest(Long boardId){
        this.boardId = boardId;
    }
}
