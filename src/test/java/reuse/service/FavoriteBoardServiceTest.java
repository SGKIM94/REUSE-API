package reuse.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reuse.domain.Board;
import reuse.domain.FavoriteBoard;
import reuse.domain.User;
import reuse.dto.board.FindWithProductResponseView;
import reuse.dto.board.ListBoardWithProductResponseView;
import reuse.repository.FavoriteBoardRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static reuse.fixture.BoardFixture.*;
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

    @BeforeEach
    void setUp() {
        this.favoriteBoardService = new FavoriteBoardService(favoriteBoardRepository, boardService);
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

    @DisplayName("해당 사용자가 즐겨찾기한 모든 게시글을 가져오는지")
    @Test
    public void findAllByUserId() {
        //given
        when(favoriteBoardRepository.findAllByUserId(any())).thenReturn(LIST_BOARD_BY_CATEGORY_RESPONSE_VIEW);

        //when
        ListBoardWithProductResponseView list = favoriteBoardService.listByUser(TEST_USER);

        FindWithProductResponseView board = list.getFirstIndex();

        //then
        assertThat(board.getContent()).isEqualTo(TEST_BOARD_CONTENT);
        assertThat(board.getTitle()).isEqualTo(TEST_BOARD_TITLE);
    }
}
