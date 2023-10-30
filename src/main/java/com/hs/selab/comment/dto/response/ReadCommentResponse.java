package com.hs.selab.comment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ReadCommentResponse {
    private Long commentId;
    private Long memberId;
    private Long postId;
    private String comment;
    private String commentWriteMemberName;
}
