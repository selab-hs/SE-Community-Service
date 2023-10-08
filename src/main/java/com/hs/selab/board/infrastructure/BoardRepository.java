package com.hs.selab.board.infrastructure;

import com.hs.selab.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    boolean existsByIdAndMemberId(Long id, Long memberId);
}
