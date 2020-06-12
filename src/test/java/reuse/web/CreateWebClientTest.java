package reuse.web;

import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.MultiValueMap;
import reuse.domain.User;
import reuse.dto.board.CreateBoardRequestView;
import reuse.dto.board.CreateBoardResponseView;
import reuse.dto.category.CreateCategoryRequestView;
import reuse.dto.category.CreateCategoryResponseView;
import reuse.dto.favorite.CreateFavoriteBoardRequestView;
import reuse.dto.product.CreateProductResponseView;

import java.util.Objects;

import static reuse.fixture.BoardFixture.CREATE_BOARD_REQUEST_VIEW;
import static reuse.fixture.FavoriteBoardFixture.getCreateFavoriteBoardRequestView;
import static reuse.fixture.UserFixture.USER_SIGH_UP_REQUEST_DTO;
import static reuse.web.BoardAcceptanceTest.BOARD_BASE_URL;
import static reuse.web.CategoryAcceptanceTest.CATEGORY_BASE_URL;
import static reuse.web.FavoriteBoardAcceptanceTest.FAVORITE_BASE_URL;
import static reuse.web.ProductAcceptanceTest.PRODUCT_BASE_URL;
import static reuse.web.UserAcceptanceTest.LOGIN_API_URL;
import static reuse.web.UserAcceptanceTest.USER_BASE_URL;

public class CreateWebClientTest extends RestWebClientTest {
    public CreateWebClientTest(WebTestClient webTestClient) {
        super(webTestClient);
    }

    String createUser() {
        return Objects.requireNonNull(
                postMethodAcceptance(USER_BASE_URL + LOGIN_API_URL, USER_SIGH_UP_REQUEST_DTO, User.class)
                .getResponseBody()
                .getSocialTokenId());
    }

    String createProduct(MultiValueMap<String, Object> product, String jwt) {
        return Objects.requireNonNull(
                postMethodWithFormData(PRODUCT_BASE_URL, product, CreateProductResponseView.class, jwt)
                        .getResponseHeaders()
                        .getLocation()
                        .getPath());
    }

    CreateBoardResponseView createBoard(CreateBoardRequestView board, String jwt) {
        return Objects.requireNonNull(
                postMethodWithAuthAcceptance
                        (BOARD_BASE_URL, board, CreateBoardResponseView.class, jwt)
                        .getResponseBody());
    }

    CreateCategoryResponseView createCategory(CreateCategoryRequestView category, String jwt) {
        return postMethodWithAuthAcceptance
                (CATEGORY_BASE_URL, category, CreateCategoryResponseView.class, jwt)
                .getResponseBody();
    }

    public CreateBoardResponseView createFavoriteBoard(String jwt) {
        CreateBoardResponseView board = createBoard(CREATE_BOARD_REQUEST_VIEW, jwt);
        CreateFavoriteBoardRequestView favorite = getCreateFavoriteBoardRequestView(board.getId());

        return postMethodWithAuthAcceptance(FAVORITE_BASE_URL, favorite, CreateBoardResponseView.class, jwt)
                .getResponseBody();
    }
}
