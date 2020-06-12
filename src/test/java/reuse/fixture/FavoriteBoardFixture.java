package reuse.fixture;

import reuse.domain.FavoriteBoard;
import reuse.dto.favorite.CreateFavoriteBoardRequestView;

import static reuse.fixture.BoardFixture.TEST_BOARD;
import static reuse.fixture.BoardFixture.TEST_FIRST_BOARD_ID;
import static reuse.fixture.UserFixture.TEST_USER;

public class FavoriteBoardFixture extends CommonFixture {
    public static final FavoriteBoard FAVORITE_BOARD
            = FavoriteBoard.builder().user(TEST_USER).board(TEST_BOARD).build();

    public static final CreateFavoriteBoardRequestView CREATE_FAVORITE_BOARD_REQUEST_VIEW
            = CreateFavoriteBoardRequestView.builder().boardId(TEST_FIRST_BOARD_ID).build();


    public static CreateFavoriteBoardRequestView getCreateFavoriteBoardRequestView(Long boardId) {
        return CreateFavoriteBoardRequestView.builder()
                .boardId(boardId)
                .build();

    }
}
