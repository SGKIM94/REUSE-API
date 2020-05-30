package reuse.web;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.MultiValueMap;
import reuse.domain.User;
import reuse.dto.board.CreateBoardRequestView;
import reuse.dto.board.CreateBoardResponseView;
import reuse.dto.product.CreateProductResponseView;

import java.util.Objects;

import static reuse.fixture.BoardFixture.CREATE_BOARD_REQUEST_VIEW;
import static reuse.fixture.UserFixture.USER_SIGH_UP_REQUEST_DTO;
import static reuse.web.BoardAcceptanceTest.BOARD_BASE_URL;
import static reuse.web.ProductAcceptanceTest.PRODUCT_BASE_URL;
import static reuse.web.UserAcceptanceTest.LOGIN_API_URL;
import static reuse.web.UserAcceptanceTest.USER_BASE_URL;

public class CreateWebClientTest extends RestWebClientTest {
    private String socialTokenId;

    @BeforeEach
    void setUp() {
        socialTokenId = createUser();
    }

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
                        (BOARD_BASE_URL, CREATE_BOARD_REQUEST_VIEW, CreateBoardResponseView.class, jwt)
                        .getResponseBody());
    }
}
