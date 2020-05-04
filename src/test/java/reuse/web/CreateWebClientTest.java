package reuse.web;

import org.springframework.test.web.reactive.server.WebTestClient;
import reuse.domain.Board;
import reuse.domain.User;
import reuse.dto.board.CreateBoardRequestView;
import reuse.dto.product.CreateProductRequestView;
import reuse.dto.product.CreateProductResponseView;

import java.util.Objects;

import static reuse.fixture.UserFixture.USER_SIGH_UP_REQUEST_DTO;
import static reuse.web.BoardAcceptanceTest.BOARD_BASE_URL;
import static reuse.web.ProductAcceptanceTest.PRODUCT_BASE_URL;
import static reuse.web.UserAcceptanceTest.USER_BASE_URL;

public class CreateWebClientTest extends RestWebClientTest {

    public CreateWebClientTest(WebTestClient webTestClient) {
        super(webTestClient);
    }

    String createUser() {
        return Objects.requireNonNull(
                postMethodAcceptance(USER_BASE_URL, USER_SIGH_UP_REQUEST_DTO, User.class)
                .getResponseHeaders()
                .getLocation()
                .getPath());
    }

    String createProduct(CreateProductRequestView product) {
        return Objects.requireNonNull(
                postMethodAcceptance(PRODUCT_BASE_URL, product, CreateProductResponseView.class)
                        .getResponseHeaders()
                        .getLocation()
                        .getPath());
    }

    String createBoard(CreateBoardRequestView board) {
        return Objects.requireNonNull(
                postMethodAcceptance(BOARD_BASE_URL, board, Board.class)
                        .getResponseHeaders()
                        .getLocation()
                        .getPath());
    }
}
