package com.core.service.board.dto.Response;

import javax.persistence.metamodel.StaticMetamodel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReadAllBoardResponse {
    private Long id;
    private Long memberId;
    private String title;
    private String content;
    private Long view;
}
