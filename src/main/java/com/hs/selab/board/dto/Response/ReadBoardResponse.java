package com.hs.selab.board.dto.Response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReadBoardResponse {
        private Long id;
        private String title;
        private String content;
}
