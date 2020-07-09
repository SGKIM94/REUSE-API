package reuse.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import reuse.domain.Board;
import reuse.dto.board.CreateBoardResponseView;
import reuse.dto.board.FindBoardResponseView;
import reuse.dto.board.ListBoardResponseView;
import reuse.dto.board.ListBoardWithProductResponseView;
import reuse.repository.BoardRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static reuse.fixture.BoardFixture.*;
import static reuse.fixture.ProductFixture.TEST_PRODUCT;
import static reuse.fixture.UserFixture.TEST_USER;

public class BoardServiceTest extends AbstractServiceTest {
    @Mock
    private BoardRepository boardRepository;

    @Mock
    private ProductService productService;

    @InjectMocks
    private BoardService boardService;


    @DisplayName("게시물이 생성되는지")
    @Test
    public void create() {
        when(productService.findById(anyLong())).thenReturn(TEST_PRODUCT);
        when(boardRepository.save(any())).thenReturn(TEST_BOARD);

        CreateBoardResponseView board = boardService.create(CREATE_BOARD_REQUEST_VIEW, TEST_USER);

        assertThat(board.getId()).isNotNull();
    }

    @DisplayName("게시물 리스트르 조회하는지")
    @Test
    public void list() {
        when(boardRepository.findAll()).thenReturn(TEST_BOARDS);

        ListBoardResponseView boards = boardService.list();

        assertThat(boards.getSize()).isEqualTo(2);
    }

    @DisplayName("특정 게시물을 ID 로 조회 가능한지")
    @Test
    public void findById() {
        when(boardRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(TEST_BOARD));

        FindBoardResponseView board = boardService.retrieve(DEFAULT_ID);

        assertThat(board.getContent()).isEqualTo(TEST_BOARD_CONTENT);
        verify(boardRepository).findById(any());
    }


    @DisplayName("게시물 상세 조회 시 존재하지 않는 경우 예외를 던지는지")
    @Test
    public void findByIdFail() {
        when(boardRepository.findById(any())).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> {
            boardService.retrieve(DEFAULT_ID);
        });
    }

    @DisplayName("게시물 수정이 가능한지")
    @Test
    public void modify() {
        when(boardRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(TEST_BOARD));

        Board modifiedBoard = boardService.modify(MODIFY_BOARD_REQUEST_DTO, DEFAULT_ID);

        assertThat(modifiedBoard.getTitle()).isEqualTo(TEST_MODIFY_BOARD_TITLE);
    }

    @DisplayName("카테고리 별 게시물 조회가 가능한지")
    @Test
    public void listByCategory() {
        when(boardRepository.findAllByCategory(any())).thenReturn(LIST_BOARD_BY_CATEGORY_RESPONSE_VIEW);

        ListBoardWithProductResponseView boards = boardService.listByCategory(LIST_BOARD_BY_CATEGORY_REQUEST_VIEW);

        assertThat(boards.getSize()).isEqualTo(2);
    }

    @DisplayName("게시글 예약 신청 시 해당 게시글을 가져와 상태값을 예약으로 변경시키는지")
    @Test
    public void reserve() {
        when(boardRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(TEST_BOARD));

        Board board = boardService.reserve(DEFAULT_ID, TEST_USER);

        assertThat(board.getSalesStatus()).isEqualTo(Board.SalesStatusType.RESERVE);
    }

    @DisplayName("게시글 판매 완료 시")
    @Test
    public void complete() {
        when(boardRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(TEST_BOARD));

        Board board = boardService.reserve(DEFAULT_ID, TEST_USER);

        assertThat(board.getSalesStatus()).isEqualTo(Board.SalesStatusType.RESERVE);
    }

}
