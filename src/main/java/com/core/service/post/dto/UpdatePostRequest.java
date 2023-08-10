package com.core.service.post.dto;

import lombok.Getter;

@Getter
public class UpdatePostRequest {
    Long boardId;
    Long commentId;
}