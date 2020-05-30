package reuse.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import reuse.AbstractAcceptanceTest;
import reuse.dto.product.CreateProductResponseView;
import reuse.dto.product.FindProductResponseView;
import reuse.dto.product.ListProductResponseView;
import reuse.security.TokenAuthenticationService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static reuse.fixture.ProductFixture.*;

public class ProductAcceptanceTest extends AbstractAcceptanceTest {
    public static final String PRODUCT_BASE_URL = "/products";

    private CreateWebClientTest createWebClientTest;
    private TokenAuthenticationService tokenAuthenticationService;
    private String socialTokenId;

    @BeforeEach
    void setUp() {
        this.createWebClientTest = new CreateWebClientTest(this.webTestClient);
        this.tokenAuthenticationService = new TokenAuthenticationService();
        socialTokenId = createWebClientTest.createUser();
    }

    @DisplayName("품목을 추가가 가능한지")
    @Test
    // TODO : 이미지를 넣어서 테스트해볼 수 있는 환경 필요
    public void createProduct() {
        //when
        EntityExchangeResult<CreateProductResponseView> expectResponse = createWebClientTest.postMethodWithFormData
                (PRODUCT_BASE_URL, getCreateProductMap(), CreateProductResponseView.class, getJwt());

        //then
        HttpHeaders responseHeaders = expectResponse.getResponseHeaders();

        //then
        assertThat(responseHeaders.getLocation()).isNotNull();
    }


    @DisplayName("품목 리스트를 조회 가능한지")
    @Test
    @Sql(scripts = {"/insert-products.sql"})
    public void listProduct() {
        //when
        EntityExchangeResult<ListProductResponseView> response
                = createWebClientTest.getMethodWithAuthAcceptance
                (PRODUCT_BASE_URL, ListProductResponseView.class, getJwt());

        //then
        HttpStatus status = response.getStatus();
        ListProductResponseView responseBody = response.getResponseBody();
        List<FindProductResponseView> products = responseBody.getProducts();

        assertThat(status).isEqualByComparingTo(HttpStatus.OK);
        assertThat(responseBody.getSize()).isEqualTo(2);
        assertThat(products.get(0).getName()).isEqualTo(TEST_PRODUCT_NAME);
        assertThat(products.get(1).getName()).isEqualTo(TEST_PRODUCT_NAME);
    }

    @DisplayName("품목 상세를 조회 가능한지")
    @Test
    @Sql(scripts = {"/insert-products.sql"})
    public void findProduct() {
        //when
        EntityExchangeResult<FindProductResponseView> response = createWebClientTest.getMethodWithAuthAcceptance
                (PRODUCT_BASE_URL + "/" + DEFAULT_ID, FindProductResponseView.class, getJwt());

        //then
        HttpStatus status = response.getStatus();
        FindProductResponseView responseBody = response.getResponseBody();

        assertThat(status).isEqualByComparingTo(HttpStatus.OK);
        assertThat(responseBody.getName()).isEqualTo(TEST_PRODUCT_NAME);
    }

    public String getJwt() {
        return tokenAuthenticationService.toJwtBySocialTokenId(socialTokenId);
    }
}
