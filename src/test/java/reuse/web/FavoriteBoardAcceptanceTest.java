package reuse.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import reuse.AbstractAcceptanceTest;
import reuse.dto.board.CreateBoardResponseView;
import reuse.dto.favorite.CreateFavoriteBoardRequestView;
import reuse.security.TokenAuthenticationService;

import static org.assertj.core.api.Assertions.assertThat;
import static reuse.fixture.BoardFixture.CREATE_BOARD_REQUEST_VIEW;
import static reuse.fixture.CategoryFixture.CREATE_CATEGORY_REQUEST_VIEW;
import static reuse.fixture.FavoriteBoardFixture.getCreateFavoriteBoardRequestView;

public class FavoriteBoardAcceptanceTest extends AbstractAcceptanceTest {
    public static final String FAVORITE_BASE_URL = "/favorites/board";

    private CreateWebClientTest restWebClientTest;
    private TokenAuthenticationService tokenAuthenticationService;
    private String socialTokenId;

    @BeforeEach
    void setUp() {
        this.restWebClientTest = new CreateWebClientTest(this.webTestClient);
        this.tokenAuthenticationService = new TokenAuthenticationService();

        socialTokenId = restWebClientTest.createUser();
        restWebClientTest.createCategory(CREATE_CATEGORY_REQUEST_VIEW, getJwt());
    }

    @DisplayName("게사판 추가가 가능한지")
    @Test
    @Sql(scripts = {"/clean-all.sql", "/insert-categories.sql", "/insert-products.sql"})
    public void createFavoriteBoard() {
        CreateBoardResponseView board = restWebClientTest.createBoard(CREATE_BOARD_REQUEST_VIEW, getJwt());
        CreateFavoriteBoardRequestView favorite = getCreateFavoriteBoardRequestView(board.getId());

        //when
        EntityExchangeResult<CreateBoardResponseView> expectResponse
                = restWebClientTest.postMethodWithAuthAcceptance
                (FAVORITE_BASE_URL, favorite, CreateBoardResponseView.class, getJwt());

        //then
        HttpStatus status = expectResponse.getStatus();
        CreateBoardResponseView responseBody = expectResponse.getResponseBody();

        assertThat(status).isEqualByComparingTo(HttpStatus.OK);
        assertThat(responseBody).isNotNull();
    }

    public String getJwt() {
        return tokenAuthenticationService.toJwtBySocialTokenId(socialTokenId);
    }
}
