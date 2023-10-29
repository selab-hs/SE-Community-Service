package com.hs.selab.board.application;

import com.hs.selab.board.dto.Response.BoardModel;
import com.hs.selab.board.infrastructure.BoardRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

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

}