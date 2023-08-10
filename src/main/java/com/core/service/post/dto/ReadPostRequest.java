package com.core.service.post.dto;

import lombok.Getter;

@Getter
public class ReadPostRequest {
    private final Long boardId;

    public ReadPostRequest(Long boardId){
        this.boardId = boardId;
    }
}