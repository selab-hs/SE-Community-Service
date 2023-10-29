package com.hs.selab.board.presentaion;

import com.hs.selab.board.application.BoardService;
import com.hs.selab.common.response.dto.ResponseDto;
import com.hs.selab.common.response.dto.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/boards")
@RequiredArgsConstructor
public class BoardRestController {

    private final BoardService boardService;

    @GetMapping("/category")
    public ResponseEntity<?> getBoardCategory() {
        return ResponseDto.toResponseEntity(
                ResponseMessage.READ_SUCCESS_BOARD_CATEGORY,
                boardService.getAll()
        );
    }
}
