package com.core.service.board.presentaion;

import com.core.service.auth.domain.UserDetail;
import com.core.service.auth.infrastructure.annotation.AuthMember;
import com.core.service.board.application.BoardService;
import com.core.service.board.dto.request.CreateBoardRequest;
import com.core.service.board.dto.request.UpdateBoardRequest;
import com.core.service.common.response.dto.ResponseDto;
import com.core.service.common.response.dto.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/boards")
@RequiredArgsConstructor
public class BoardRestController {

    private final BoardService boardService;

    /**
     * 게시글을 전체 조회.
     *
     * @param pageable
     * @return search all board Response
     * @tobo type 별로 검색을 받아 조회 하게 만들 예정
     */
    @GetMapping
    public ResponseEntity<?> getAllBoards(
        @PageableDefault(sort = "id", size = 15, direction = Direction.ASC)
        Pageable pageable
    ) {
        var boards = boardService.getAll(pageable);

        return ResponseDto.toResponseEntity(
            ResponseMessage.READ_SUCCESS_ALL_BOARD,
            boards
        );
    }

    /**
     * 게시글 상세 조회.
     *
     * @param id-> 게시판 ID
     * @return search Detail board Response
     * @tobo 인증 받은 member만 조회 가능하게 변경
     */
    @GetMapping("{id}")
    public ResponseEntity<?> getBoards(
        @PathVariable("id") Long id,
        @AuthMember UserDetail userInfo
    ) {
        boardService.updateBoardView(id);
        var board = boardService.get(id, userInfo);

        return ResponseDto.toResponseEntity(
            ResponseMessage.READ_SUCCESS_BOARD,
            board
        );
    }

    /**
     * 게시글 생성.
     *
     * @param request 게시글 생성에 필요한 data들
     * @return create board
     * @tobo 이미지도 업로드 가능하게 변경 예정
     */
    @PostMapping
    public ResponseEntity<?> createBoard(
        @RequestBody CreateBoardRequest request,
        @AuthMember UserDetail userInfo
    ) {
        var boardId = boardService.create(request, userInfo);
        boardService.createBoardView(boardId);

        return ResponseDto.toResponseEntity(
            ResponseMessage.CREATE_SUCCESS_BOARD,
            "게시판 생성 성공"
        );
    }

    /**
     * 게시글 변경.
     *
     * @param request 게시글에 변경 사항(title, content)
     * @return update board 게시글 변경
     * @tobo 이미지도 변경가능하게 변경, 생성자만 변경 할 수 있게 변경
     */
    @PatchMapping("{id}")
    public ResponseEntity<?> updateBoards(
        @PathVariable("id") Long id,
        @RequestBody UpdateBoardRequest request,
        @AuthMember UserDetail userInfo
    ) {
        boardService.update(id, request, userInfo);

        return ResponseDto.toResponseEntity(
            ResponseMessage.UPDATE_SUCCESS_BOARD,
            "게시판 수정 성공"
        );
    }

    /**
     * 게시글 삭제.
     *
     * @param id 삭제 해당 게시글 id
     * @return delete board 게시글 삭제
     * @tobo - 게시글과 영속성을 갖일 댓글도 같이 삭제 가능하게 변경할 예정 - 작성자만 게시글을 삭제 가능하게 변경한 예정
     */
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteBoard(
        @PathVariable("id") Long id,
        @AuthMember UserDetail userInfo
    ) {
        boardService.delete(id, userInfo);

        return ResponseDto.toResponseEntity(
            ResponseMessage.DELETE_SUCCESS_BOARD,
            "게시판 삭제 성공"
        );
    }
}
