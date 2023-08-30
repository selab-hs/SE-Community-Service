package com.core.service.board.infrastructure;

import com.core.service.board.domain.BoardView;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardViewRepository extends JpaRepository<BoardView, Long> {
    Optional<BoardView> findByBoardId(Long boardId);
}