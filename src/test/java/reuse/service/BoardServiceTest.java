package reuse.service;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import reuse.domain.Board;
import reuse.domain.SalesStatusType;
import reuse.dto.board.CreateBoardResponseView;
import reuse.dto.board.FindBoardResponseView;
import reuse.dto.board.ListBoardResponseView;
import reuse.dto.board.ListBoardWithProductResponseView;
import reuse.repository.BoardRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static reuse.fixture.BoardFixture.*;
import static reuse.fixture.ProductFixture.TEST_PRODUCT;
import static reuse.fixture.ProductImagesFixture.FIRST_IMAGE_URL;
import static reuse.fixture.UserFixture.TEST_SECOND_USER;
import static reuse.fixture.UserFixture.TEST_USER;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BoardServiceTest extends AbstractServiceTest {
    @Mock
    private BoardRepository boardRepository;

    @InjectMocks
    private BoardService boardService;

    private Board testBoard;

    @BeforeEach
    void setUp() {
        this.testBoard = Board.builder().id(TEST_FIRST_BOARD_ID).title(TEST_BOARD_TITLE).content(TEST_BOARD_CONTENT)
                .product(TEST_PRODUCT).sellerAddress(TEST_SELLER_ADDRESS).buyer(TEST_SECOND_USER).seller(TEST_USER)
                .salesStatus(SalesStatusType.SALE).build();
    }

    @DisplayName("게시물이 생성되는지")
    @Test
    @Order(1)
    public void create() {
        when(boardRepository.save(any())).thenReturn(testBoard);

        CreateBoardResponseView board = boardService.create(CREATE_BOARD_REQUEST_VIEW, TEST_USER);

        assertThat(board.getId()).isNotNull();
    }

    @DisplayName("게시물 리스트르 조회하는지")
    @Test
    @Order(2)
    public void list() {
        when(boardRepository.findAll()).thenReturn(TEST_BOARDS);

        ListBoardResponseView boards = boardService.list();

        assertThat(boards.getSize()).isEqualTo(2);
    }

    @DisplayName("특정 게시물을 ID 로 조회 가능한지")
    @Test
    @Order(3)
    public void findById() {
        when(boardRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(testBoard));

        FindBoardResponseView board = boardService.retrieve(DEFAULT_ID);

        assertThat(board.getContent()).isEqualTo(TEST_BOARD_CONTENT);
        assertThat(board.getProduct().getMainImage()).isEqualTo(FIRST_IMAGE_URL);
        verify(boardRepository).findById(any());
    }


    @DisplayName("게시물 상세 조회 시 존재하지 않는 경우 예외를 던지는지")
    @Test
    @Order(4)
    public void findByIdFail() {
        when(boardRepository.findById(any())).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> {
            boardService.retrieve(DEFAULT_ID);
        });
    }

    @DisplayName("카테고리 별 게시물 조회가 가능한지")
    @Test
    @Order(5)
    public void listByCategory() {
        when(boardRepository.findAllByCategory(any())).thenReturn(LIST_BOARD_BY_CATEGORY_RESPONSE_VIEW);

        ListBoardWithProductResponseView boards = boardService.listByCategory(LIST_BOARD_BY_CATEGORY_REQUEST_VIEW);

        assertThat(boards.getSize()).isEqualTo(2);
    }

    @DisplayName("게시글 예약 신청 시 해당 게시글을 가져와 상태값을 예약으로 변경시키는지")
    @Test
    @Order(6)
    public void reserve() {
        when(boardRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(testBoard));

        Board board = boardService.reserve(MODIFY_BOARD_STATUS_REQUEST_VIEW, TEST_USER);

        assertThat(board.getSalesStatus()).isEqualTo(SalesStatusType.RESERVE);
    }

    @DisplayName("게시글 판매 완료 시")
    @Test
    @Order(7)
    public void complete() {
        when(boardRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(testBoard));

        Board board = boardService.reserve(MODIFY_BOARD_STATUS_REQUEST_VIEW, TEST_USER);

        assertThat(board.getSalesStatus()).isEqualTo(SalesStatusType.RESERVE);
    }

    @DisplayName("게시물 수정이 가능한지")
    @Test
    @Order(8)
    public void modify() {
        when(boardRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(testBoard));

        Board modifiedBoard = boardService.modify(MODIFY_BOARD_REQUEST_DTO, DEFAULT_ID);

        assertThat(modifiedBoard.getTitle()).isEqualTo(TEST_MODIFY_BOARD_TITLE);
    }
}
