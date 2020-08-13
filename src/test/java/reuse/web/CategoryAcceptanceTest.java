package reuse.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import reuse.AbstractAcceptanceTest;
import reuse.domain.User;
import reuse.dto.GroupResponseView;
import reuse.dto.category.CreateCategoryRequestView;
import reuse.dto.category.CreateCategoryResponseView;
import reuse.dto.category.FindCategoryResponseView;
import reuse.dto.category.ListCategoryView;
import reuse.security.TokenAuthenticationService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static reuse.fixture.CategoryFixture.*;

public class CategoryAcceptanceTest extends AbstractAcceptanceTest {
    public static final String CATEGORY_BASE_URL = "/categories";

    private CreateWebClientTest createWebClientTest;
    private TokenAuthenticationService tokenAuthenticationService;
    private User loginUser;

    @BeforeEach
    void setUp() {
        this.createWebClientTest = new CreateWebClientTest(this.webTestClient);
        this.tokenAuthenticationService = new TokenAuthenticationService();
        loginUser = createWebClientTest.createUser();
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
        CreateCategoryResponseView category = createWebClientTest.createCategory(CREATE_CATEGORY_REQUEST_VIEW, getJwt());

        //when
        EntityExchangeResult<FindCategoryResponseView> response
                = createWebClientTest.getMethodWithAuthAcceptance
                (CATEGORY_BASE_URL + "/" + category.getId(), FindCategoryResponseView.class, getJwt());

        //then
        HttpStatus status = response.getStatus();
        FindCategoryResponseView responseBody = response.getResponseBody();

        assertThat(status).isEqualByComparingTo(HttpStatus.OK);

        assertThat(responseBody.getManufacturer()).isEqualTo(TEST_MANUFACTURER);
        assertThat(responseBody.getModel()).isEqualTo(TEST_MODEL);
        assertThat(responseBody.getTelco()).isEqualTo(TEST_TELECO);
        assertThat(responseBody.getDeviceChange()).isEqualTo(TEST_DEVICE_CHANGE);
    }


    @DisplayName("카테고리의 모든 종류를 조회 가능한지")
    @Test
    public void  listCategory() {
        //when
        EntityExchangeResult<ListCategoryView> response = createWebClientTest.getMethodWithAuthAcceptance
                (CATEGORY_BASE_URL, ListCategoryView.class, getJwt());

        //then
        HttpStatus status = response.getStatus();
        ListCategoryView responseBody = response.getResponseBody();

        assertThat(status).isEqualByComparingTo(HttpStatus.OK);
        List<GroupResponseView> manufacturers = responseBody.getManufacturers().getGroup();
        List<GroupResponseView> models = responseBody.getModels().getGroup();
        List<GroupResponseView> telecos = responseBody.getTelecos().getGroup();

        assertThat(manufacturers.size()).isEqualTo(5);
        assertThat(models.size()).isEqualTo(11);
        assertThat(telecos.size()).isEqualTo(6);
    }

    public String getJwt() {
        return tokenAuthenticationService.toJwtBySocialTokenId(loginUser.getSocialTokenId());
    }
}
