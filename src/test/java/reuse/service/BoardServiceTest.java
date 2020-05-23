package reuse.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reuse.dto.board.CreateBoardResponseView;
import reuse.dto.board.ListBoardResponseView;
import reuse.repository.BoardRepository;
import reuse.repository.ProductRepository;
import reuse.security.TokenAuthenticationService;
import reuse.storage.S3Uploader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static reuse.fixture.BoardFixture.*;
import static reuse.fixture.ProductFixture.TEST_PRODUCT;

@SpringBootTest
public class BoardServiceTest {
    private BoardService boardService;

    @MockBean
    private BoardRepository boardRepository;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private S3Uploader s3Uploader;

    @MockBean
    private TokenAuthenticationService tokenAuthenticationService;

    @BeforeEach
    void setUp() {
        this.tokenAuthenticationService = new TokenAuthenticationService();
        ProductService productService = new ProductService(productRepository, s3Uploader);
        this.boardService = new BoardService(boardRepository, productService);
    }

    @DisplayName("게시물이 생성되는지")
    @Test
    public void create() {
        when(boardRepository.save(any())).thenReturn(TEST_BOARD);
        when(productRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(TEST_PRODUCT));

        CreateBoardResponseView board = boardService.create(CREATE_BOARD_REQUEST_VIEW);

        assertThat(board.getId()).isNotNull();
        assertThat(board.getTitle()).isEqualTo(TEST_BOARD_TITLE);
        assertThat(board.getContent()).isEqualTo(TEST_BOARD_CONTENT);
        assertThat(board.getProduct()).isNotNull();
        assertThat(board.getSellerAddress()).isEqualTo(TEST_SELLER_ADDRESS);
    }

    @Disabled
    @DisplayName("게시물 리스트르 조회하는지")
    @Test
    public void list() {
        when(boardRepository.findAll()).thenReturn(TEST_BOARDS);

        ListBoardResponseView boards = boardService.list();

        assertThat(boards.getSize()).isEqualTo(2);
    }

    @Disabled
    @DisplayName("게시물 수정이 가능한지")
    @Test
    public void update() {
        boardService.modify(MODIFY_BOARD_REQUEST_DTO);

        // find 추가 후 검증하는 메서드필요
    }
}
