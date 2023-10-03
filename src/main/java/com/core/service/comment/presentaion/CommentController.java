package com.core.service.comment.presentaion;

import com.core.service.auth.domain.UserDetail;
import com.core.service.auth.infrastructure.annotation.AuthMember;
import com.core.service.comment.application.CommentService;
import com.core.service.comment.dto.request.CreateCommentRequest;
import com.core.service.comment.dto.request.ReadCommentRequest;
import com.core.service.comment.dto.request.UpdateCommentRequest;
import com.core.service.common.response.dto.ResponseDto;
import com.core.service.common.response.dto.ResponseMessage;
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
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping()
    public ResponseEntity<?> createComment(
        @AuthMember UserDetail userInfo,
        @RequestBody CreateCommentRequest request
    ){
        commentService.create(request, userInfo);

        return ResponseDto.toResponseEntity(
            ResponseMessage.CREATE_SUCCESS_COMMENT,
            "댓글 생성에 성공"
        );
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<?> updateComment(
        @PathVariable Long commentId,
        @AuthMember UserDetail userInfo,
        @RequestBody UpdateCommentRequest request){

        commentService.update(commentId, request, userInfo);

        return ResponseDto.toResponseEntity(
            ResponseMessage.UPDATE_SUCCESS_COMMENT,
            "댓글 수정에 성공"
        );
    }

    @GetMapping()
    public ResponseEntity<?> getAllComments(
        @RequestBody ReadCommentRequest request,
        @AuthMember UserDetail userInfo
        ){
        var comments =  commentService.getAll(request, userInfo);

        return ResponseDto.toResponseEntity(ResponseMessage.READ_ALL_SUCCESS_COMMENT, comments);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComments(
        @PathVariable Long commentId,
        @AuthMember UserDetail userInfo
    ){
        commentService.delete(commentId, userInfo);

        return ResponseDto.toResponseEntity(
            ResponseMessage.DELETE_SUCCESS_COMMENT,
            "해당 board 댓글 삭제 성공");
    }
}
