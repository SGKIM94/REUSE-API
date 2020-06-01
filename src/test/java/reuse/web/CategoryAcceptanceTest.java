package reuse.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import reuse.AbstractAcceptanceTest;
import reuse.dto.category.CreateCategoryRequestView;
import reuse.security.TokenAuthenticationService;

import static org.assertj.core.api.Assertions.assertThat;
import static reuse.fixture.CategoryFixture.CREATE_CATEGORY_REQUEST_VIEW;

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

    public String getJwt() {
        return tokenAuthenticationService.toJwtBySocialTokenId(socialTokenId);
    }
}
