package com.hs.selab.board.domain.converter;

import com.hs.selab.board.domain.Board;
import com.hs.selab.board.dto.Response.ReadAllBoardResponse;
import com.hs.selab.board.dto.Response.ReadBoardResponse;
import com.hs.selab.board.dto.request.CreateBoardRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BoardConverter {

    public Board convertToBoardEntity(CreateBoardRequest request) {
        return Board.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }


    public ReadBoardResponse convertToReadBoardResponse(Board board) {
        return ReadBoardResponse.builder()
                .id(board.getId())
                .name(board.getName())
                .description(board.getDescription())
                .build();
    }

    public List<ReadAllBoardResponse> convertToReadAllBoardResponse(List<Board> boards) {
        return boards.stream().map(
                board -> ReadAllBoardResponse.builder()
                        .id(board.getId())
                        .name(board.getName())
                        .description(board.getDescription())
                        .build()
        ).collect(Collectors.toList());
    }

}
