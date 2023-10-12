package com.hs.selab.board.application;

import static com.hs.selab.error.dto.ErrorMessage.NON_EXISTENT_BOARD_EXCEPTION;
import static com.hs.selab.error.dto.ErrorMessage.UNAUTHORIZED_ACCESS_EXCEPTION;

import com.hs.selab.auth.domain.UserDetail;
import com.hs.selab.board.domain.converter.BoardConverter;
import com.hs.selab.board.dto.Response.BoardModel;
import com.hs.selab.board.dto.Response.ReadAllBoardResponse;
import com.hs.selab.board.dto.Response.ReadBoardResponse;
import com.hs.selab.board.dto.request.CreateBoardRequest;
import com.hs.selab.board.dto.request.UpdateBoardRequest;
import com.hs.selab.board.infrastructure.BoardRepository;
import com.hs.selab.error.exception.board.NonExistentBoardException;
import com.hs.selab.error.exception.member.UnauthorizedAccessException;
import com.hs.selab.member.domain.vo.RoleType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardConverter converter;

    private Map<Long, BoardModel> boardModels;

    @Scheduled(fixedRate = 1000 * 60 * 3, initialDelayString = "0")
    public void refreshBoardModels() {
        log.info("refresh boardModels Info Start");
        boardModels = refresh();
        log.info("refresh boardModels Info Complete");
    }

    private Map<Long, BoardModel> refresh() {
        return boardRepository.findAll()
                .stream()
                .map(BoardModel::of)
                .collect(Collectors.toMap(BoardModel::getId, Function.identity()));
    }

    public List<BoardModel> getAll() {
        return new ArrayList<>(boardModels.values());
    }

/*   @Transactional
    public Long create(CreateBoardRequest request, UserDetail userInfo){
        if (!userInfo.getRoleType().equals(RoleType.USER))
        {
            throw new UnauthorizedAccessException(
                UNAUTHORIZED_ACCESS_EXCEPTION,
                "권한이 없는 접근입니다."
            );
        }
        var board = converter.convertToBoardEntity(request);
        boardRepository.save(board);

        return board.getId();
    }*/

//    @Transactional
//    public void update(UpdateBoardRequest request, Long boardId, UserDetail userInfo){
//        if (!boardRepository.existsByIdAndMemberId(boardId, userInfo.getId()))
//        {
//            throw new UnauthorizedAccessException(
//                UNAUTHORIZED_ACCESS_EXCEPTION,
//                "권한이 없는 접근입니다."
//            );
//        }
//
//        var board = boardRepository.findById(boardId).orElseThrow(
//            () -> new NonExistentBoardException(
//                NON_EXISTENT_BOARD_EXCEPTION, "업데이트 단일 게시판 조회 실패")
//        );
//        board.update(request);
//        boardRepository.save(board);
//    }
//
//
//    @Transactional(readOnly = true)
//    public ReadBoardResponse get(
//        Long boardId,
//        UserDetail userInfo
//    ) {
//        if (!userInfo.getRoleType().equals(RoleType.LAB_USER))
//        {
//            throw new UnauthorizedAccessException(
//                UNAUTHORIZED_ACCESS_EXCEPTION,
//                "권한이 없는 접근입니다."
//            );
//        }
//
//        var board = boardRepository.findById(boardId).orElseThrow(
//            () -> new NonExistentBoardException(
//                NON_EXISTENT_BOARD_EXCEPTION, "업데이트 단일 게시판 조회 실패")
//        );
//
//        return converter.convertToReadBoardResponse(board);
//    }
//
//    @Transactional(readOnly = true)
//    public List<ReadAllBoardResponse> getAll(
//        UserDetail userInfo
//    ) {
//        if (!userInfo.getRoleType().equals(RoleType.LAB_USER))
//        {
//            throw new UnauthorizedAccessException(
//                UNAUTHORIZED_ACCESS_EXCEPTION,
//                "권한이 없는 접근입니다."
//            );
//        }
//        var boards = boardRepository.findAll();
//        return converter.convertToReadAllBoardResponse(boards);
//    }
//
//    @Transactional
//    public void delete(Long boardId, UserDetail userInfo) {
//        if (!boardRepository.existsByIdAndMemberId(boardId, userInfo.getId()))
//        {
//            throw new UnauthorizedAccessException(
//                UNAUTHORIZED_ACCESS_EXCEPTION,
//                "권한이 없는 접근입니다."
//            );
//        }
//        boardRepository.deleteById(boardId);
//    }
}
