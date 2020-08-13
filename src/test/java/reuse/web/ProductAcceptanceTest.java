package reuse.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import reuse.AbstractAcceptanceTest;
import reuse.domain.User;
import reuse.dto.category.CreateCategoryResponseView;
import reuse.dto.product.CreateProductResponseView;
import reuse.dto.product.FindProductResponseView;
import reuse.dto.product.ListProductResponseView;
import reuse.security.TokenAuthenticationService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static reuse.fixture.CategoryFixture.CREATE_CATEGORY_REQUEST_VIEW;
import static reuse.fixture.ProductFixture.*;

public class ProductAcceptanceTest extends AbstractAcceptanceTest {
    public static final String PRODUCT_BASE_URL = "/products";

    private CreateWebClientTest createWebClientTest;
    private TokenAuthenticationService tokenAuthenticationService;
    private User loginUser;

    @BeforeEach
    void setUp() {
        this.createWebClientTest = new CreateWebClientTest(this.webTestClient);
        this.tokenAuthenticationService = new TokenAuthenticationService();
        loginUser = createWebClientTest.createUser();
    }

    @DisplayName("품목을 추가가 가능한지")
    @Test
    // TODO : 이미지를 넣어서 테스트해볼 수 있는 환경 필요
    public void createProduct() {
        //given
        CreateCategoryResponseView category = createWebClientTest.createCategory(CREATE_CATEGORY_REQUEST_VIEW, getJwt());

        //when
        EntityExchangeResult<CreateProductResponseView> expectResponse = createWebClientTest.postMethodWithFormData
                (PRODUCT_BASE_URL, getCreateProductMap(category.getId()), CreateProductResponseView.class, getJwt());

        //then
        CreateProductResponseView product = expectResponse.getResponseBody();

        //then
        assertThat(product.getId()).isNotNull();
    }

    @DisplayName("품목 리스트를 조회 가능한지")
    @Test
    @Sql(scripts = {"/clean-all.sql", "/insert-categories.sql", "/insert-products.sql"})
    public void listProduct() {
        //when
        EntityExchangeResult<ListProductResponseView> response = createWebClientTest.getMethodWithAuthAcceptance
                (PRODUCT_BASE_URL, ListProductResponseView.class, getJwt());

        //then
        HttpStatus status = response.getStatus();
        ListProductResponseView responseBody = response.getResponseBody();
        List<FindProductResponseView> products = responseBody.getProducts();

        assertThat(status).isEqualByComparingTo(HttpStatus.OK);
        assertThat(responseBody.getSize()).isEqualTo(8);
        assertThat(products.get(0).getName()).isNotBlank();
        assertThat(products.get(1).getName()).isNotBlank();
    }

    @DisplayName("품목 상세를 조회 가능한지")
    @Test
    @Sql(scripts = {"/clean-all.sql", "/insert-categories.sql", "/insert-products.sql"})
    public void findProduct() {
        //when
        EntityExchangeResult<FindProductResponseView> response = createWebClientTest.getMethodWithAuthAcceptance
                (PRODUCT_BASE_URL + "/" + TEST_PRODUCT_ID, FindProductResponseView.class, getJwt());

        //then
        HttpStatus status = response.getStatus();
        FindProductResponseView responseBody = response.getResponseBody();

        assertThat(status).isEqualByComparingTo(HttpStatus.OK);
        assertThat(responseBody.getName()).isEqualTo(TEST_SECOND_PRODUCT_NAME);
    }

    public String getJwt() {
        return tokenAuthenticationService.toJwtBySocialTokenId(loginUser.getSocialTokenId());
    }
}
