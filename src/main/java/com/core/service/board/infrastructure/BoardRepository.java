package com.core.service.board.infrastructure;

import com.core.service.board.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Page<Board> findAll(Pageable pageable);

    boolean existsByIdAndMemberId(Long id, Long memberId);
}