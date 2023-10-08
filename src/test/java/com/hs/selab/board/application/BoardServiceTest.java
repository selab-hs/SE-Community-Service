//package com.core.service.board.application;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.mockito.Mockito.atLeastOnce;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import com.core.service.auth.domain.UserDetail;
//import com.core.service.board.domain.Board;
//import com.core.service.board.domain.converter.BoardConverter;
//import com.core.service.board.dto.Response.ReadAllBoardResponse;
//import com.core.service.board.dto.Response.ReadBoardResponse;
//import com.core.service.board.dto.request.CreateBoardRequest;
//import com.core.service.board.dto.request.UpdateBoardRequest;
//import com.core.service.board.infrastructure.BoardRepository;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//
//@ExtendWith(MockitoExtension.class)
//class BoardServiceTest {
//
//    @Mock
//    BoardRepository boardRepository;
//
//    @Mock
//    BoardConverter boardConverter;
//
//    @InjectMocks
//    BoardService boardService;
//
//
//    @Test
//    @DisplayName("게시판 작성 Test")
//    void create() {
//        //given
//        CreateBoardRequest createBoardRequest = new CreateBoardRequest();
//        createBoardRequest.setTitle("제목");
//        createBoardRequest.setContent("내용");
//
//        Board board = Board.builder()
//            .id(1L)
//            .title(createBoardRequest.getTitle())
//            .content(createBoardRequest.getContent())
//            .memberId(12L)
//            .build();
//
//        when(boardConverter.convertToBoardEntity(createBoardRequest, )).thenReturn(board);
//
//        //when
//        boardService.create(createBoardRequest);
//
//        //then
//        verify(boardConverter, atLeastOnce()).convertToBoardEntity(createBoardRequest);
//        verify(boardRepository, atLeastOnce()).save(board);
//    }
//
//    @Test
//    @DisplayName("게시판 수정 Test")
//    public void update() {
//        // Given
//        Long boardId = 1L;
//        UpdateBoardRequest updateRequest = new UpdateBoardRequest();
//        Board board = Board.builder().build();
//        when(boardRepository.findById(boardId)).thenReturn(Optional.of(board));
//
//        // When
//        boardService.update(boardId, updateRequest);
//
//        // Then
//        verify(boardRepository, times(1)).findById(boardId);
//        verify(boardRepository, times(1)).save(board);
//    }
//
//    @Test
//    @DisplayName("단일 조회 Test")
//    public void get(){
//        //given
//        Long boardId =1L;
//        Board board = Board.builder()
//            .id(boardId)
//            .build();
//        when(boardRepository.findById(boardId)).thenReturn(Optional.of(board));
//        when(boardConverter.convertToReadBoardResponse(board))
//            .thenReturn(ReadBoardResponse
//                .builder()
//                .id(1L)
//                .build());
//
//        // When
//        ReadBoardResponse result = boardService.get(boardId);
//
//        // Then
//        verify(boardRepository, times(1)).findById(boardId);
//        verify(boardConverter, times(1)).convertToReadBoardResponse(board);
//
//        Assertions.assertThat(boardId).isEqualTo(result.getId());
//    }
//
//    @Test
//    @DisplayName("전체 조회 Test")
//    public void getAll(){
//        // Given
//        Pageable pageable = Pageable.ofSize(10).withPage(0);
//
//        List<Board> boardList = Arrays.asList(Board.builder().build(), Board.builder().build());
//        Page<Board> boardPage = new PageImpl<>(boardList, pageable, boardList.size());
//        when(boardRepository.findAll(pageable)).thenReturn(boardPage);
//
//        List<ReadAllBoardResponse> responseList = Arrays.asList(
//            ReadAllBoardResponse.builder().build(), ReadAllBoardResponse.builder().build());
//        Page<ReadAllBoardResponse> responsePage = new PageImpl<>(responseList, pageable, responseList.size());
//        when(boardConverter.convertToReadAllBoardResponse(boardPage))
//            .thenReturn(responsePage);
//
//        // When
//        Page<ReadAllBoardResponse> result = boardService.getAll(pageable);
//
//        // Then
//        assertThat(boardPage.getSize()).isEqualTo(result.getSize());
//        verify(boardRepository, times(1)).findAll(pageable);
//        verify(boardConverter, times(1)).convertToReadAllBoardResponse(boardPage);
//    }
//}