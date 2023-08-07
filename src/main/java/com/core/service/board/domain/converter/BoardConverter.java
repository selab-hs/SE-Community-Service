package com.core.service.board.domain.converter;

import com.core.service.board.domain.Board;
import com.core.service.board.dto.Response.ReadAllBoardResponse;
import com.core.service.board.dto.Response.ReadBoardResponse;
import com.core.service.board.dto.request.CreateBoardRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class BoardConverter {

    public Board convertToBoardEntity(CreateBoardRequest request){
        return Board.builder()
            .id(request.getId())
            .memberId(request.getMemberId())
            .title(request.getTitle())
            .content(request.getContent())
            .build();
    }

    public ReadBoardResponse convertToReadBoardResponse(Board board){
        return ReadBoardResponse.builder()
            .id(board.getId())
            .memberId(board.getMemberId())
            .title(board.getTitle())
            .content(board.getContent())
            .build();
    }

    public Page<ReadAllBoardResponse> convertToReadAllBoardResponse(Page<Board> boards){
        return boards.map(
            board -> ReadAllBoardResponse.builder()
                .id(board.getId())
                .memberId(board.getMemberId())
                .title(board.getTitle())
                .content(board.getContent())
                .view(board.getViewCount())
                .build());
    }
}