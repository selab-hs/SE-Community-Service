package com.hs.selab.board.presentaion;

import com.hs.selab.auth.domain.UserDetail;
import com.hs.selab.auth.infrastructure.annotation.AuthMember;
import com.hs.selab.board.application.BoardService;
import com.hs.selab.board.dto.request.CreateBoardRequest;
import com.hs.selab.board.dto.request.UpdateBoardRequest;
import com.hs.selab.common.response.dto.ResponseDto;
import com.hs.selab.common.response.dto.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/boards")
@RequiredArgsConstructor
public class BoardRestController {

    private final BoardService boardService;

/*    @GetMapping
    public ResponseEntity<?> getAllBoards(
        @AuthMember UserDetail userInfo
    ) {
        var boards = boardService.getAll(userInfo);

        return ResponseDto.toResponseEntity(
            ResponseMessage.READ_SUCCESS_ALL_BOARD,
            boards
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getBoards(
        @PathVariable("id") Long id,
        @AuthMember UserDetail userInfo
    ) {
        var board =boardService.get(id, userInfo);

        return ResponseDto.toResponseEntity(
            ResponseMessage.READ_SUCCESS_BOARD,
            board
        );
    }
*/
    @PostMapping
    public ResponseEntity<?> createBoard(
        @RequestBody CreateBoardRequest request,
        @AuthMember UserDetail userInfo
    ) {
        var boardId = boardService.create(request, userInfo);

        return ResponseDto.toResponseEntity(
            ResponseMessage.CREATE_SUCCESS_BOARD,
            boardId
        );
    }
/*
    @PatchMapping("{id}")
    public ResponseEntity<?> updateBoards(
        @PathVariable("id") Long id,
        @RequestBody UpdateBoardRequest request,
        @AuthMember UserDetail userInfo
    ) {
        boardService.update(request, id, userInfo);

        return ResponseDto.toResponseEntity(
            ResponseMessage.UPDATE_SUCCESS_BOARD,
            HttpStatus.OK
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteBoard(
        @PathVariable("id") Long id,
        @AuthMember UserDetail userInfo
    ) {
        boardService.delete(id, userInfo);

        return ResponseDto.toResponseEntity(
            ResponseMessage.DELETE_SUCCESS_BOARD,
            HttpStatus.OK
        );
    }*/
}
