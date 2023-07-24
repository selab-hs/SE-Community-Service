package com.core.service.board.dto.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateBoardRequest {
    private Long id;
    private String memberId;
    private String title;
    private String content;
}
