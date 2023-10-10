package com.hs.selab.board.domain.converter;

import com.hs.selab.board.domain.Board;
import com.hs.selab.board.dto.Response.ReadAllBoardResponse;
import com.hs.selab.board.dto.Response.ReadBoardResponse;
import com.hs.selab.board.dto.request.CreateBoardRequest;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class BoardConverter {

    public Board convertToBoardEntity(CreateBoardRequest request) {
        return Board.builder()
            .title(request.getTitle())
            .content(request.getContent())
            .build();
    }


    public ReadBoardResponse convertToReadBoardResponse(Board board) {
        return ReadBoardResponse.builder()
            .id(board.getId())
            .title(board.getTitle())
            .content(board.getContent())
            .build();
    }

    public List<ReadAllBoardResponse> convertToReadAllBoardResponse(List<Board> boards){
        return boards.stream().map(
            board -> ReadAllBoardResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .build()
        ).collect(Collectors.toList());
    }

}