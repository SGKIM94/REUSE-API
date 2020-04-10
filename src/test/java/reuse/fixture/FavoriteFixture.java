package reuse.fixture;

import reuse.domain.Favorite;
import reuse.domain.Item;
import reuse.dto.favorite.FavoriteCreateRequestView;
import reuse.dto.favorite.FavoriteCreateResponseView;

import java.util.Arrays;
import java.util.List;

import static reuse.fixture.UserFixture.TEST_USER;

public class FavoriteFixture {
    public static final Long FAVORITE_ID = 0L;
    public static final Long STATION_FIRST_ID = 1L;
    public static final Long EDGE_FIRST_ID = 1L;
    public static final Long EDGE_SECOND_ID = 2L;

    public static final Item TEST_ITEM = new Item(1L);
    public static final Favorite NEW_STATION_FAVORITE = new Favorite(TEST_USER, TEST_ITEM);
    public static final Favorite NEW_SECOND_STATION_FAVORITE = new Favorite(TEST_USER, TEST_ITEM);

    public static final Favorite NEW_EDGE_FAVORITE = new Favorite(TEST_USER, TEST_ITEM);
    public static final Favorite NEW_SECOND_EDGE_FAVORITE = new Favorite(TEST_USER, TEST_ITEM);

    public static final List<Favorite> NEW_STATION_FAVORITES = Arrays.asList(NEW_STATION_FAVORITE, NEW_SECOND_STATION_FAVORITE);
    public static final List<Favorite> NEW_EDGE_FAVORITES = Arrays.asList(NEW_EDGE_FAVORITE, NEW_SECOND_EDGE_FAVORITE);

    public static final FavoriteCreateRequestView STATION_FAVORITE_CREATE_REQUEST_VIEW
            = new FavoriteCreateRequestView(TEST_ITEM, "");
    public static final FavoriteCreateRequestView EDGE_FAVORITE_CREATE_REQUEST_VIEW
            = new FavoriteCreateRequestView(TEST_ITEM, "");

    public static final FavoriteCreateResponseView FAVORITE_CREATE_RESPONSE_VIEW
            = new FavoriteCreateResponseView(FAVORITE_ID, TEST_USER, TEST_ITEM);
}
