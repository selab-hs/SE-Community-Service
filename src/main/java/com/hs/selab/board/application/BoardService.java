package com.hs.selab.board.application;

import static com.hs.selab.error.dto.ErrorMessage.NON_EXISTENT_BOARD_EXCEPTION;
import static com.hs.selab.error.dto.ErrorMessage.UNAUTHORIZED_ACCESS_EXCEPTION;

import com.hs.selab.auth.domain.UserDetail;
import com.hs.selab.board.domain.converter.BoardConverter;
import com.hs.selab.board.dto.Response.ReadAllBoardResponse;
import com.hs.selab.board.dto.Response.ReadBoardResponse;
import com.hs.selab.board.dto.request.CreateBoardRequest;
import com.hs.selab.board.dto.request.UpdateBoardRequest;
import com.hs.selab.board.event.BoardViewEvent;
import com.hs.selab.board.infrastructure.BoardRepository;
import com.hs.selab.board.infrastructure.BoardViewRepository;
import com.hs.selab.error.exception.board.NonExistentBoardException;
import com.hs.selab.error.exception.member.UnauthorizedAccessException;
import com.hs.selab.member.domain.vo.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardViewRepository boardViewRepository;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final BoardConverter converter;

    @Transactional
    public Long create(CreateBoardRequest request, UserDetail userInfo) {
        if (!(userInfo.getRoleType().equals(RoleType.LAB_LEADER) ||
            userInfo.getRoleType().equals(RoleType.LAB_USER)))
        {
            throw new UnauthorizedAccessException(
                UNAUTHORIZED_ACCESS_EXCEPTION,
                "권한이 없는 접근입니다."
            );
        }
        var board = converter.convertToBoardEntity(request, userInfo);
        boardRepository.save(board);

        return board.getId();
    }

    @Async
    @Transactional
    public void createBoardView(Long boardId){
        var boardView = converter.convertToEventBoardView(boardId);
        boardViewRepository.save(boardView);
    }

    @Transactional
    public void update(Long boardId, UpdateBoardRequest request, UserDetail userInfo) {
        if (!(boardRepository.existsByIdAndMemberId(boardId, userInfo.getId())))
        {
            throw new UnauthorizedAccessException(
                UNAUTHORIZED_ACCESS_EXCEPTION,
                "권한이 없는 접근입니다."
            );
        }
        var board = boardRepository.findById(boardId)
            .orElseThrow(() -> new NonExistentBoardException(
                    NON_EXISTENT_BOARD_EXCEPTION,
                    "업데이트 단일 게시판 조회 실패"
                )
            );
        board.update(request);
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public ReadBoardResponse get(
        Long boardId,
        UserDetail userInfo
    ) {
        if (!(userInfo.getRoleType().equals(RoleType.LAB_LEADER) ||
            userInfo.getRoleType().equals(RoleType.LAB_USER)))
        {
            throw new UnauthorizedAccessException(
                UNAUTHORIZED_ACCESS_EXCEPTION,
                "권한이 없는 접근입니다."
            );
        }

        return converter.convertToReadBoardResponse
            (
                boardRepository.findById(boardId)
                    .orElseThrow(
                        () -> new NonExistentBoardException(
                            NON_EXISTENT_BOARD_EXCEPTION,
                            "단일 게시판 조회 실패")),
                boardViewRepository.findByBoardId(boardId)
                    .orElseThrow(() -> new NonExistentBoardException(
                            NON_EXISTENT_BOARD_EXCEPTION,
                            "업데이트 단일 게시판 조회 실패"))
                    .getBoardView()
            );
    }

    @Transactional(readOnly = true)
    public Page<ReadAllBoardResponse> getAll(Pageable pageable) {
        return converter.convertToReadAllBoardResponse(
            boardRepository.findAll(),
            boardViewRepository.findAll(),
            pageable
        );
    }


    @Transactional
    public void updateBoardView(Long boardId) {
        applicationEventPublisher.publishEvent(new BoardViewEvent(boardId));
    }

    @Transactional
    public void delete(Long boardId, UserDetail userInfo) {
        if (!(boardRepository.existsByIdAndMemberId(boardId, userInfo.getId()) &&
            (userInfo.getRoleType().equals(RoleType.LAB_LEADER) ||
                userInfo.getRoleType().equals(RoleType.LAB_USER))))
        {
            throw new UnauthorizedAccessException(
                UNAUTHORIZED_ACCESS_EXCEPTION,
                "권한이 없는 접근입니다."
            );
        }
        boardRepository.deleteById(boardId);
    }
}