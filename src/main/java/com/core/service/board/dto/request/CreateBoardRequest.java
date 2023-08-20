package com.core.service.board.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBoardRequest {
    private Long id;
    private String memberId;
    private String title;
    private String content;
}
