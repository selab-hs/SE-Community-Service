package com.hs.selab.board.dto.Response;

import com.hs.selab.board.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardModel {
    private Long id;
    private String title;
    private String content;

    public static BoardModel of(Board board) {
        return new BoardModel(board.getId(), board.getTitle(), board.getContent());
    }
}
