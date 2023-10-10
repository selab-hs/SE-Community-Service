package com.hs.selab.comment.dto.request;

import lombok.Getter;

@Getter
public class CreateCommentRequest {

    private Long boardId;
    private Long postId;
    private String comment;
}
