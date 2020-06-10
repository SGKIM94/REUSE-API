package reuse.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reuse.domain.Board;
import reuse.domain.FavoriteBoard;
import reuse.domain.User;
import reuse.repository.FavoriteBoardRepository;
import reuse.security.TokenAuthenticationService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static reuse.fixture.BoardFixture.TEST_BOARD;
import static reuse.fixture.BoardFixture.TEST_BOARD_TITLE;
import static reuse.fixture.FavoriteBoardFixture.CREATE_FAVORITE_BOARD_REQUEST_VIEW;
import static reuse.fixture.FavoriteBoardFixture.FAVORITE_BOARD;
import static reuse.fixture.UserFixture.KIM_NAME;
import static reuse.fixture.UserFixture.TEST_USER;

@SpringBootTest
public class FavoriteBoardServiceTest {
    private FavoriteBoardService favoriteBoardService;

    @MockBean
    private FavoriteBoardRepository favoriteBoardRepository;

    @MockBean
    private BoardService boardService;

    @MockBean
    private TokenAuthenticationService tokenAuthenticationService;

    @BeforeEach
    void setUp() {
        this.tokenAuthenticationService = new TokenAuthenticationService();
        this.favoriteBoardService = new FavoriteBoardService(favoriteBoardRepository);
    }

    @DisplayName("게시물애 대한 즐겨찾기가 추가되는지")
    @Test
    public void create() {
        //given
        when(favoriteBoardRepository.save(any())).thenReturn(FAVORITE_BOARD);
        when(boardService.findById(any())).thenReturn(TEST_BOARD);

        //when
        FavoriteBoard savedBoard = favoriteBoardService.create(CREATE_FAVORITE_BOARD_REQUEST_VIEW, TEST_USER);

        User user = savedBoard.getUser();
        Board board = savedBoard.getBoard();

        //then
        assertThat(user.getName()).isEqualTo(KIM_NAME);
        assertThat(board.getTitle()).isEqualTo(TEST_BOARD_TITLE);
    }
}
