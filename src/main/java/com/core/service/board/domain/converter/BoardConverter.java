package com.core.service.board.domain.converter;

import com.core.service.auth.domain.UserDetail;
import com.core.service.board.domain.Board;
import com.core.service.board.domain.BoardView;
import com.core.service.board.dto.Response.ReadAllBoardResponse;
import com.core.service.board.dto.Response.ReadBoardResponse;
import com.core.service.board.dto.request.CreateBoardRequest;
import com.core.service.common.util.dto.ListToPageConverter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class BoardConverter {

    public Board convertToBoardEntity(CreateBoardRequest request, UserDetail userInfo) {
        return Board.builder()
            .memberId(userInfo.getId())
            .title(request.getTitle())
            .content(request.getContent())
            .build();
    }
    public BoardView convertToEventBoardView(Long boardId){
        return BoardView.builder()
            .boardId(boardId)
            .boardView(0L)
            .build();
    }


    public ReadBoardResponse convertToReadBoardResponse(Board board, Long boardView) {
        return ReadBoardResponse.builder()
            .id(board.getId())
            .memberId(board.getMemberId())
            .title(board.getTitle())
            .content(board.getContent())
            .view(boardView)
            .build();
    }

    public Page<ReadAllBoardResponse> convertToReadAllBoardResponse(
        List<Board> boards,
        List<BoardView> boardViews,
        Pageable pageable
    ){
       List<ReadAllBoardResponse> result = new ArrayList<>();
        for(int i =0; i<boards.size();i++){
           var board = boards.get(i);
           var boardView = boardViews.get(i);

           result.add(
             ReadAllBoardResponse.builder()
                 .id(board.getId())
                 .memberId(board.getMemberId())
                 .title(board.getTitle())
                 .content(board.getTitle())
                 .view(boardView.getBoardView())
                 .build()
           );
       }
        return ListToPageConverter.convert(
            result,
            pageable.getPageNumber(),
            pageable.getPageSize()
        );
    }
}