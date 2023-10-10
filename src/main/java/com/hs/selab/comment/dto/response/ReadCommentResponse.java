package com.hs.selab.comment.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReadCommentResponse {
    private Long commentId;
    private Long memberId;
    private Long boardId;
    private Long postId;
    private String comment;
}
