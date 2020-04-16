package reuse.web;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import reuse.AbstractAcceptanceTest;
import reuse.domain.Product;
import reuse.domain.User;
import reuse.security.TokenAuthenticationService;

import static reuse.fixture.UserFixture.*;

public class ProductAcceptanceTest extends AbstractAcceptanceTest {
    public static final String KIM_INPUT_JSON = "{\"email\":\"" + KIM_EMAIL + "\",\"password\":\"" + KIM_PASSWORD + "\",\"name\":\"" + KIM_NAME + "\"}";;
    public static final String PRODUCT_BASE_URL = "/products";

    private CreateWebClientTest restWebClientTest;
    private TokenAuthenticationService tokenAuthenticationService;

    @BeforeEach
    void setUp() {
        cleanAllDatabases();
        this.restWebClientTest = new CreateWebClientTest(this.webTestClient);
        this.tokenAuthenticationService = new TokenAuthenticationService();
    }

    @DisplayName("품목을 추가가 가능한지")
    @Test
    public void createProduct(SoftAssertions softly) {
        //when
        EntityExchangeResult<Product> expectResponse
                = restWebClientTest.postMethodAcceptance(PRODUCT_BASE_URL, KIM_INPUT_JSON, Product.class);

        //then
        HttpHeaders responseHeaders = expectResponse.getResponseHeaders();
        Product responseBody = expectResponse.getResponseBody();

        //then
        softly.assertThat(responseHeaders.getLocation()).isNotNull();
    }
}
