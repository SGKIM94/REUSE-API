package reuse.web;

import org.springframework.test.web.reactive.server.WebTestClient;
import reuse.domain.Product;
import reuse.domain.User;
import reuse.dto.product.CreateProductRequestView;
import reuse.dto.product.CreateProductResponseView;

import java.util.Objects;

import static reuse.fixture.ProductFixture.CREATE_PRODUCT_REQUEST_DTO;
import static reuse.web.ProductAcceptanceTest.PRODUCT_BASE_URL;
import static reuse.web.UserAcceptanceTest.KIM_INPUT_JSON;
import static reuse.web.UserAcceptanceTest.USER_BASE_URL;

public class CreateWebClientTest extends RestWebClientTest {

    public CreateWebClientTest(WebTestClient webTestClient) {
        super(webTestClient);
    }

    String createUser() {
        return Objects.requireNonNull(
                postMethodAcceptance(USER_BASE_URL + "/sigh-up", KIM_INPUT_JSON, User.class)
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
}
