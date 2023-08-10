package com.core.service.comment.presentaion;

import com.core.service.comment.application.CommentService;
import com.core.service.comment.domain.Comment;
import com.core.service.comment.dto.request.CreateCommentRequest;
import com.core.service.comment.dto.request.UpdateCommentRequest;
import com.core.service.comment.dto.response.ReadCommentResponse;
import com.core.service.common.response.dto.ResponseDto;
import com.core.service.common.response.dto.ResponseMessage;
import com.core.service.post.application.PostService;
import com.core.service.post.dto.CreatePostRequest;
import com.core.service.post.dto.ReadPostRequest;
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
    private final PostService postService;

    @PostMapping("/{boardId}")
    public ResponseEntity<ResponseDto> createComment(
        @PathVariable Long boardId,
        @RequestBody CreateCommentRequest request){
        Comment comment = commentService.create(request);
        postService.linkBoardAndComment(new CreatePostRequest(boardId, comment.getId()));

        return ResponseDto.toResponseEntity(ResponseMessage.CREATE_SUCCESS_COMMENT,"댓글 생성에 성공");
    }

    @PatchMapping("/{boardId}/{commentId}")
    public ResponseEntity<ResponseDto> updateComment(
        @PathVariable Long boardId,
        @PathVariable Long commentId,
        @RequestBody UpdateCommentRequest request){
        commentService.update(boardId, commentId, request);

        return ResponseDto.toResponseEntity(ResponseMessage.UPDATE_SUCCESS_COMMENT,"댓글 수정에 성공");
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<ResponseDto> getAllComments(@PathVariable Long boardId){
        List<ReadCommentResponse> comments =  commentService.getAll(
            postService.getAllCommentsInBoard(new ReadPostRequest(boardId)));

        return ResponseDto.toResponseEntity(ResponseMessage.READ_ALL_SUCCESS_COMMENT,comments);
    }

    @DeleteMapping("/{boardId}/{commentId}")
    public ResponseEntity<ResponseDto> getAllComments(
        @PathVariable Long boardId,
        @PathVariable Long commentId){
        commentService.delete(boardId, commentId);

        return ResponseDto.toResponseEntity(ResponseMessage.DELETE_SUCCESS_COMMENT,"해당 board 댓글 삭제 성공");
    }
}
