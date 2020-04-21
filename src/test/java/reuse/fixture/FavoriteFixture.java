package reuse.fixture;

import reuse.domain.Item;
import reuse.dto.favorite.CreateFavoriteRequestView;
import reuse.dto.favorite.CreateFavoriteResponseView;

import static reuse.fixture.UserFixture.TEST_USER;

public class FavoriteFixture {
    public static final Long FAVORITE_ID = 0L;

    public static final Item TEST_ITEM = new Item(1L);

    public static final CreateFavoriteRequestView FAVORITE_CREATE_REQUEST_VIEW
            = new CreateFavoriteRequestView(TEST_ITEM, "");
    public static final CreateFavoriteRequestView EDGE_FAVORITE_CREATE_REQUEST_VIEW
            = new CreateFavoriteRequestView(TEST_ITEM, "");

    public static final CreateFavoriteResponseView FAVORITE_CREATE_RESPONSE_VIEW
            = new CreateFavoriteResponseView(FAVORITE_ID, TEST_USER, TEST_ITEM);
}
