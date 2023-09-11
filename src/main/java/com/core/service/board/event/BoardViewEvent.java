package com.core.service.board.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BoardViewEvent {
    private Long boardId;
}