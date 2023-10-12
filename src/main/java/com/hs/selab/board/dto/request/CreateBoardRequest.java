package com.hs.selab.board.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBoardRequest {
    private String name;
    private String description;
}
