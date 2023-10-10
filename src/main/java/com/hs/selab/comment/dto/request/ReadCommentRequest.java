package com.hs.selab.comment.dto.request;

import lombok.Getter;

@Getter
public class ReadCommentRequest {
    private final Long postId;

    public ReadCommentRequest(Long postId){
        this.postId = postId;
    }
}
