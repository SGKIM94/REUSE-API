package reuse.web;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import reuse.AbstractAcceptanceTest;
import reuse.dto.product.CreateProductResponseView;
import reuse.security.TokenAuthenticationService;

import static reuse.fixture.ProductFixture.CREATE_PRODUCT_REQUEST_DTO;

public class ProductAcceptanceTest extends AbstractAcceptanceTest {
    public static final String PRODUCT_BASE_URL = "/products";

    private CreateWebClientTest restWebClientTest;
    private TokenAuthenticationService tokenAuthenticationService;

    @BeforeEach
    void setUp() {
        this.restWebClientTest = new CreateWebClientTest(this.webTestClient);
        this.tokenAuthenticationService = new TokenAuthenticationService();
    }

    @DisplayName("품목을 추가가 가능한지")
    @Test
    public void createProduct(SoftAssertions softly) {
        //when
        EntityExchangeResult<CreateProductResponseView> expectResponse = restWebClientTest.postMethodAcceptance
                (PRODUCT_BASE_URL, CREATE_PRODUCT_REQUEST_DTO, CreateProductResponseView.class);

        //then
        HttpHeaders responseHeaders = expectResponse.getResponseHeaders();

        //then
        softly.assertThat(responseHeaders.getLocation()).isNotNull();
    }
}
