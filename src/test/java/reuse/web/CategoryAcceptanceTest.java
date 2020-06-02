package reuse.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import reuse.AbstractAcceptanceTest;
import reuse.dto.category.CreateCategoryRequestView;
import reuse.dto.category.FindCategoryResponseView;
import reuse.security.TokenAuthenticationService;

import static org.assertj.core.api.Assertions.assertThat;
import static reuse.fixture.CategoryFixture.*;

public class CategoryAcceptanceTest extends AbstractAcceptanceTest {
    public static final String CATEGORY_BASE_URL = "/categories";

    private CreateWebClientTest createWebClientTest;
    private TokenAuthenticationService tokenAuthenticationService;
    private String socialTokenId;

    @BeforeEach
    void setUp() {
        this.createWebClientTest = new CreateWebClientTest(this.webTestClient);
        this.tokenAuthenticationService = new TokenAuthenticationService();
        socialTokenId = createWebClientTest.createUser();
    }

    @DisplayName("카테고리 추가가 가능한지")
    @Test
    public void createCategory() {
        //when
        EntityExchangeResult<CreateCategoryRequestView> expectResponse = createWebClientTest.postMethodWithAuthAcceptance
                (CATEGORY_BASE_URL, CREATE_CATEGORY_REQUEST_VIEW, CreateCategoryRequestView.class, getJwt());

        HttpStatus status = expectResponse.getStatus();

        assertThat(status).isEqualTo(HttpStatus.OK);
    }

    @DisplayName("카테고리 상세 조회 가능한지")
    @Test
    public void retrieveCategory() {
        String categoryId = createWebClientTest.createCategory(CREATE_CATEGORY_REQUEST_VIEW, getJwt());

        //when
        EntityExchangeResult<FindCategoryResponseView> response
                = createWebClientTest.getMethodWithAuthAcceptance
                (CATEGORY_BASE_URL + "/" + categoryId, FindCategoryResponseView.class, getJwt());

        //then
        HttpStatus status = response.getStatus();
        FindCategoryResponseView responseBody = response.getResponseBody();

        assertThat(status).isEqualByComparingTo(HttpStatus.OK);

        assertThat(responseBody.getManufacturer()).isEqualTo(TEST_MANUFACTURER);
        assertThat(responseBody.getModel()).isEqualTo(TEST_MODEL);
        assertThat(responseBody.getTelco()).isEqualTo(TEST_TELECO);
    }


    public String getJwt() {
        return tokenAuthenticationService.toJwtBySocialTokenId(socialTokenId);
    }
}
