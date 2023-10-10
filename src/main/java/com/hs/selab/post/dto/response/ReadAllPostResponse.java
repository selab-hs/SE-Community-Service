package com.hs.selab.post.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReadAllPostResponse {
    private Long id;
    private Long memberId;
    private String title;
    private String content;
    private Long view;
}
