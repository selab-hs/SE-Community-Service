package com.core.service.board.dto.Response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReadAllBoardResponse {
    private Long id;
    private String memberId;
    private String title;
    private String content;
    private Long view;
}
