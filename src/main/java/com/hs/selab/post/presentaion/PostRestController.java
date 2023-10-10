package com.hs.selab.post.presentaion;

import com.hs.selab.auth.domain.UserDetail;
import com.hs.selab.auth.infrastructure.annotation.AuthMember;
import com.hs.selab.common.response.dto.ResponseDto;
import com.hs.selab.common.response.dto.ResponseMessage;
import com.hs.selab.post.application.PostService;
import com.hs.selab.post.dto.request.CreatePostRequest;
import com.hs.selab.post.dto.request.UpdatePostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostRestController {

    private final PostService postService;


    @GetMapping
    public ResponseEntity<?> getAllPosts(
        @PageableDefault(sort = "id", size = 15, direction = Direction.ASC)
        Pageable pageable,
        @RequestParam(value = "boardId") Long id
    ) {
        var posts = postService.getAll(pageable,id);

        return ResponseDto.toResponseEntity(
            ResponseMessage.READ_SUCCESS_ALL_BOARD,
            posts
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getPosts(
        @PathVariable("id") Long id,
        @AuthMember UserDetail userInfo
    ) {
        postService.updatePostView(id);
        var post = postService.get(id, userInfo);

        return ResponseDto.toResponseEntity(
            ResponseMessage.READ_SUCCESS_BOARD,
            post
        );
    }


    @PostMapping
    public ResponseEntity<?> createPost(
        @RequestBody CreatePostRequest request,
        @AuthMember UserDetail userInfo,
        @RequestParam(value = "boardId") Long id
    ) {
        var postId = postService.create(request, userInfo, id);
        postService.createPostView(postId, id);

        return ResponseDto.toResponseEntity(
            ResponseMessage.CREATE_SUCCESS_BOARD,
            "게시판 생성 성공"
        );
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> updatePost(
        @PathVariable("id") Long id,
        @RequestBody UpdatePostRequest request,
        @AuthMember UserDetail userInfo
    ) {
        postService.update(id, request, userInfo);

        return ResponseDto.toResponseEntity(
            ResponseMessage.UPDATE_SUCCESS_BOARD,
            "게시판 수정 성공"
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletePost(
        @PathVariable("id") Long id,
        @AuthMember UserDetail userInfo
    ) {
        postService.delete(id, userInfo);

        return ResponseDto.toResponseEntity(
            ResponseMessage.DELETE_SUCCESS_BOARD,
            "게시판 삭제 성공"
        );
    }
}
