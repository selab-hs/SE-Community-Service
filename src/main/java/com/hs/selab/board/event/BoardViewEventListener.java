package com.hs.selab.board.event;

import static com.hs.selab.error.dto.ErrorMessage.NON_EXISTENT_BOARD_EXCEPTION;

import com.hs.selab.board.infrastructure.BoardViewRepository;
import com.hs.selab.error.exception.board.NonExistentBoardException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BoardViewEventListener {

    private final BoardViewRepository boardViewRepository;

    @Async
    @EventListener
    public void updateBoardView(BoardViewEvent boardViewEvent) {
        var boardView = boardViewRepository.findByBoardId(boardViewEvent.getBoardId())
            .orElseThrow(() -> new NonExistentBoardException(
                    NON_EXISTENT_BOARD_EXCEPTION,
                    "업데이트 단일 게시판 조회 실패"
                )
            );
        boardView.update();
        boardViewRepository.save(boardView);
    }
}
