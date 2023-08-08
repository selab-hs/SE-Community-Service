package com.core.service.board.application;

import static com.core.service.error.dto.ErrorMessage.NON_EXISTENT_BOARD_EXCEPTION;

import com.core.service.board.domain.Board;
import com.core.service.board.domain.converter.BoardConverter;
import com.core.service.board.dto.Response.ReadAllBoardResponse;
import com.core.service.board.dto.Response.ReadBoardResponse;
import com.core.service.board.dto.request.CreateBoardRequest;
import com.core.service.board.dto.request.UpdateBoardRequest;
import com.core.service.board.infrastructure.BoardRepository;
import com.core.service.error.exception.board.NonExistentBoardException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardConverter converter;

    @Transactional
    public void create(CreateBoardRequest request) {
        boardRepository.save(
            converter.convertToBoardEntity(request)
        );
    }

    @Transactional
    public void update(Long boardId, UpdateBoardRequest request) {
        Board board = boardRepository.findById(boardId).orElseThrow(
            () -> new NonExistentBoardException(NON_EXISTENT_BOARD_EXCEPTION,"업데이트 단일 게시판 조회 실패")
        );
        board.update(request);
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public ReadBoardResponse get(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(
            () -> new NonExistentBoardException(NON_EXISTENT_BOARD_EXCEPTION,"단일 게시판 조회 실패")
        );
        return converter.convertToReadBoardResponse(board);
    }

    @Transactional(readOnly = true)
    public Page<ReadAllBoardResponse> getAll(Pageable pageable) {
        return converter.convertToReadAllBoardResponse(boardRepository.findAll(pageable));
    }

    @Transactional
    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public Long plusView(Long id){
        Board board = boardRepository.findById(id).orElseThrow(
            () -> new NonExistentBoardException(NON_EXISTENT_BOARD_EXCEPTION, "게시글 조회 단일 게시판 조회 실패"));
        board.updateView();
        boardRepository.save(board);

        return board.getViewCount();
    }
}