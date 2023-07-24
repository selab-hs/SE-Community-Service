package com.core.service.board.presentaion;

import com.core.service.board.application.BoardService;
import com.core.service.board.dto.Response.ReadAllBoardResponse;
import com.core.service.board.dto.Response.ReadBoardResponse;
import com.core.service.board.dto.request.CreateBoardRequest;
import com.core.service.board.dto.request.UpdateBoardRequest;
import com.core.service.common.response.dto.ResponseDto;
import com.core.service.common.response.dto.ResponseMessage;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public ResponseEntity<ResponseDto> getAllBoards(){
        List<ReadAllBoardResponse> boards = boardService.getAll();

        return ResponseDto.toResponseEntity(ResponseMessage.READ_SUCCESS_ALL_BOARD, boards);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseDto> getBoards(@PathVariable("id") Long id){
        ReadBoardResponse board = boardService.get(id);

        return ResponseDto.toResponseEntity(ResponseMessage.READ_SUCCESS_BOARD, board);
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createBoard(@RequestBody CreateBoardRequest request){
        boardService.create(request);

        return ResponseDto.toResponseEntity(ResponseMessage.CREATE_SUCCESS_BOARD, "게시판 생성 성공");
    }

    @PatchMapping("{id}")
    public ResponseEntity<ResponseDto> updateBoards(
        @PathVariable("id") Long id, @RequestBody UpdateBoardRequest request){
        boardService.update(id, request);

        return ResponseDto.toResponseEntity(ResponseMessage.UPDATE_SUCCESS_BOARD, "게시판 수정 성공");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseDto> deleteBoard(@PathVariable("id") Long id){
        boardService.delete(id);

        return ResponseDto.toResponseEntity(ResponseMessage.DELETE_SUCCESS_BOARD, "게시판 삭제 성공");
    }
}