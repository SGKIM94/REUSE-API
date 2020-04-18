package reuse.fixture;

import reuse.domain.Item;
import reuse.dto.favorite.FavoriteCreateRequestView;
import reuse.dto.favorite.FavoriteCreateResponseView;

import static reuse.fixture.UserFixture.TEST_USER;

public class FavoriteFixture {
    public static final Long FAVORITE_ID = 0L;

    public static final Item TEST_ITEM = new Item(1L);

    public static final FavoriteCreateRequestView FAVORITE_CREATE_REQUEST_VIEW
            = new FavoriteCreateRequestView(TEST_ITEM, "");
    public static final FavoriteCreateRequestView EDGE_FAVORITE_CREATE_REQUEST_VIEW
            = new FavoriteCreateRequestView(TEST_ITEM, "");

    public static final FavoriteCreateResponseView FAVORITE_CREATE_RESPONSE_VIEW
            = new FavoriteCreateResponseView(FAVORITE_ID, TEST_USER, TEST_ITEM);
}
