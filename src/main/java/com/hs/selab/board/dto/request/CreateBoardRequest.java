package com.hs.selab.board.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBoardRequest {
    private String title;
    private String content;
}
