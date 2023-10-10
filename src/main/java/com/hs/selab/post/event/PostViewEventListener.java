package com.hs.selab.post.event;

import static com.hs.selab.error.dto.ErrorMessage.NON_EXISTENT_BOARD_EXCEPTION;

import com.hs.selab.error.exception.board.NonExistentBoardException;
import com.hs.selab.post.infrastructure.PostViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostViewEventListener {

    private final PostViewRepository postViewRepository;

    @Async
    @EventListener
    public void updateBoardView(PostViewEvent postViewEvent) {
        var postView = postViewRepository.findByPostId(postViewEvent.getPostId())
            .orElseThrow(() -> new NonExistentBoardException(
                    NON_EXISTENT_BOARD_EXCEPTION,
                    "업데이트 단일 게시글 조회 실패"
                )
            );
        postView.update();
        postViewRepository.save(postView);
    }
}