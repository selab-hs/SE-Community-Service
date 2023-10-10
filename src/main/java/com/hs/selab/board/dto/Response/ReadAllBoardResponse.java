package com.hs.selab.board.dto.Response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReadAllBoardResponse {
    private Long id;
    private String title;
    private String content;
}
