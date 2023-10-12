package com.hs.selab.post.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ReadAllPostResponse {
    private Long id;
    private Long boardId;
    private Long memberId;
    private String title;
    private String content;
    private Long view;
}
