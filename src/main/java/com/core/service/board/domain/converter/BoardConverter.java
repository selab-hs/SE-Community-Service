package com.core.service.board.domain.converter;

import com.core.service.board.domain.Board;
import com.core.service.board.dto.Response.ReadAllBoardResponse;
import com.core.service.board.dto.Response.ReadBoardResponse;
import com.core.service.board.dto.request.CreateBoardRequest;
import java.util.ArrayList;
import java.util.List;
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

    public List<ReadAllBoardResponse> convertToReadAllBoardResponse(List<Board> boards){
        List<ReadAllBoardResponse> boardsResponse = new ArrayList<>();

        for(Board board : boards){
            ReadAllBoardResponse.builder()
                .id(board.getId())
                .memberId(board.getMemberId())
                .title(board.getTitle())
                .content(board.getContent())
                .view(board.getViewCount())
                .build();
        }

        return boardsResponse;
    }
}