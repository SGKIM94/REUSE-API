package reuse.web;

import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.MultiValueMap;
import reuse.domain.User;
import reuse.dto.board.CreateBoardRequestView;
import reuse.dto.board.CreateBoardResponseView;
import reuse.dto.board.ModifyBoardStatusRequestView;
import reuse.dto.category.CreateCategoryRequestView;
import reuse.dto.category.CreateCategoryResponseView;
import reuse.dto.favorite.CreateFavoriteBoardRequestView;
import reuse.dto.product.CreateProductResponseView;
import reuse.dto.user.CreateUserRequestView;

import java.util.Objects;

import static reuse.fixture.BoardFixture.CREATE_BOARD_REQUEST_VIEW;
import static reuse.fixture.BuyerReviewFixture.getCreateBuyerReviewRequestView;
import static reuse.fixture.FavoriteBoardFixture.getCreateFavoriteBoardRequestView;
import static reuse.fixture.UserFixture.USER_SIGH_UP_REQUEST_DTO;
import static reuse.web.BoardAcceptanceTest.BOARD_BASE_URL;
import static reuse.web.BuyerReviewAcceptanceTest.BUYER_REVIEW_BASE_URL;
import static reuse.web.CategoryAcceptanceTest.CATEGORY_BASE_URL;
import static reuse.web.FavoriteBoardAcceptanceTest.FAVORITE_BASE_URL;
import static reuse.web.ProductAcceptanceTest.PRODUCT_BASE_URL;
import static reuse.web.UserAcceptanceTest.LOGIN_API_URL;
import static reuse.web.UserAcceptanceTest.USER_BASE_URL;

public class CreateWebClientTest extends RestWebClientTest {
    public CreateWebClientTest(WebTestClient webTestClient) {
        super(webTestClient);
    }

    public User createUser() {
        return Objects.requireNonNull(
                postMethodAcceptance(USER_BASE_URL + LOGIN_API_URL, USER_SIGH_UP_REQUEST_DTO, User.class)
                .getResponseBody());
    }

    User createUser(CreateUserRequestView user) {
        return Objects.requireNonNull(
                postMethodAcceptance(USER_BASE_URL + LOGIN_API_URL, user, User.class)
                        .getResponseBody());
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

    public CreateCategoryResponseView createCategory(CreateCategoryRequestView category, String jwt) {
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

    public Long createBuyerReview(Long boardId, String jwt) {
        reserveBoard(new ModifyBoardStatusRequestView(boardId), jwt);
        completeBoard(new ModifyBoardStatusRequestView(boardId), jwt);

        return postMethodWithAuthAcceptance
                (BUYER_REVIEW_BASE_URL, getCreateBuyerReviewRequestView(boardId), Long.class, jwt)
                .getResponseBody();

    }

    public void reserveBoard(ModifyBoardStatusRequestView board, String jwt) {
        postMethodWithAuthAcceptance
                (BOARD_BASE_URL + "/reservation", board, Void.class, jwt).getResponseBody();
    }

    public void completeBoard(ModifyBoardStatusRequestView board, String jwt) {
        postMethodWithAuthAcceptance
                (BOARD_BASE_URL + "/complete", board, Void.class, jwt).getResponseBody();
    }
}
