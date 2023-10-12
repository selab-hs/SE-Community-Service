package com.hs.selab.board.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBoardRequest {
    private String name;
    private String description;
}
