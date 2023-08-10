package com.core.service.post.application;

import com.core.service.error.dto.ErrorMessage;
import com.core.service.error.exception.board.NonExistentBoardException;
import com.core.service.post.domain.Post;
import com.core.service.post.dto.CreatePostRequest;
import com.core.service.post.dto.DeletePostRequest;
import com.core.service.post.dto.ReadPostRequest;
import com.core.service.post.dto.UpdatePostRequest;
import com.core.service.post.infrastructure.PostRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public void linkBoardAndComment(CreatePostRequest request){
        postRepository.save(
            new Post(request.getBoardId(), request.getCommentId())
        );
    }

    @Transactional
    public void cutBoardAndComment(UpdatePostRequest request){
        Post post = postRepository.findByBoardId(request.getBoardId()).orElseThrow(
            () -> new NonExistentBoardException(ErrorMessage.NON_EXISTENT_BOARD_EXCEPTION,"해당 post 존재 하지 않음")
        );
        post.deleteCommentId(request.getCommentId());
        postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public List<Long> getAllCommentsInBoard(ReadPostRequest request){
        return postRepository.findByBoardId(request.getBoardId()).orElseThrow(
            () -> new NonExistentBoardException(ErrorMessage.NON_EXISTENT_BOARD_EXCEPTION,"해당 post 존재 하지 않음")
        ).getCommentIds();
    }

    @Transactional
    public void deletePost(DeletePostRequest request){
        postRepository.deleteByBoardId(request.getBoardId());
    }
}