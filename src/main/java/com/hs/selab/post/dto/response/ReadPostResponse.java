package com.hs.selab.post.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReadPostResponse {
        private Long id;
        private Long boardId;
        private Long memberId;
        private String title;
        private String content;
        private Long view;
}
