package com.core.service.board.application;

import com.core.service.board.domain.Board;
import com.core.service.board.domain.converter.BoardConverter;
import com.core.service.board.dto.Response.ReadAllBoardResponse;
import com.core.service.board.dto.Response.ReadBoardResponse;
import com.core.service.board.dto.request.CreateBoardRequest;
import com.core.service.board.dto.request.UpdateBoardRequest;
import com.core.service.board.infrastructure.BoardRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
            () -> new NullPointerException("해당 보드가 존재하지 않습니다.")
        );
        board.update(request);
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public ReadBoardResponse get(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(
            () -> new NullPointerException("해당 보드가 존재하지 않습니다.")
        );
        return converter.convertToReadBoardResponse(board);
    }

    @Transactional(readOnly = true)
    public List<ReadAllBoardResponse> getAll() {
        return converter.convertToReadAllBoardResponse(boardRepository.findAll());
    }

    @Transactional
    public void delete(Long id) {
        boardRepository.deleteById(id);
    }
}