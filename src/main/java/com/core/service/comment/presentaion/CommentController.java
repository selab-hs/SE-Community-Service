package com.core.service.comment.presentaion;

import com.core.service.comment.application.CommentService;
import com.core.service.comment.dto.request.CreateCommentRequest;
import com.core.service.comment.dto.request.UpdateCommentRequest;
import com.core.service.comment.dto.response.ReadCommentResponse;
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
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<ResponseDto> createComment(@RequestBody CreateCommentRequest request){
        commentService.create(request);

        return ResponseDto.toResponseEntity(ResponseMessage.CREATE_SUCCESS_COMMENT,"댓글 생성에 성공");
    }

    @PatchMapping("/{board_id}/{comment_id}")
    public ResponseEntity<ResponseDto> updateComment(
        @PathVariable("board_id") Long boardId,
        @PathVariable("comment_id")Long commentId,
        @RequestBody UpdateCommentRequest request){
        commentService.update(boardId, commentId, request);

        return ResponseDto.toResponseEntity(ResponseMessage.UPDATE_SUCCESS_COMMENT,"댓글 수정에 성공");
    }

    @GetMapping("/{board_id}")
    public ResponseEntity<ResponseDto> getAllComments(@PathVariable("board_id") Long boardId){
        List<ReadCommentResponse> comments =  commentService.getAll(boardId);

        return ResponseDto.toResponseEntity(ResponseMessage.READ_ALL_SUCCESS_COMMENT,"해당 board 전체 댓글 조회 성공");
    }

    @DeleteMapping("/{board_id}/{comment_id}")
    public ResponseEntity<ResponseDto> getAllComments(
        @PathVariable("board_id") Long boardId,
        @PathVariable("comment_id")Long commentId){
        commentService.delete(boardId, commentId);

        return ResponseDto.toResponseEntity(ResponseMessage.DELETE_SUCCESS_COMMENT,"해당 board 댓글 삭제 성공");
    }
}
