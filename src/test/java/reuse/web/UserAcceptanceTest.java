package reuse.web;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import reuse.AbstractAcceptanceTest;
import reuse.dto.user.CreateUserResponseView;
import reuse.dto.user.FindByIdResponseView;
import reuse.dto.user.LoginUserResponseView;
import reuse.security.TokenAuthenticationService;

import static org.assertj.core.api.Assertions.assertThat;
import static reuse.fixture.UserFixture.*;

@Disabled
public class UserAcceptanceTest extends AbstractAcceptanceTest {
    public static final String KIM_INPUT_JSON = "{\"email\":\"" + KIM_EMAIL + "\",\"password\":\"" + KIM_PASSWORD + "\",\"name\":\"" + KIM_NAME + "\"}";;
    public static final String USER_BASE_URL = "/users";
    public static final String LOGIN_API_URL = "/login";

    private CreateWebClientTest restWebClientTest;
    private TokenAuthenticationService tokenAuthenticationService;

    @BeforeEach
    void setUp() {
        this.restWebClientTest = new CreateWebClientTest(this.webTestClient);
        this.tokenAuthenticationService = new TokenAuthenticationService();
    }

    @DisplayName("한번도 로그인 경험이 없는 사용자가 로그인")
    @Test
    public void createUser() {
        EntityExchangeResult<CreateUserResponseView> expectResponse
                = restWebClientTest.postMethodAcceptance(USER_BASE_URL, USER_SIGH_UP_REQUEST_DTO, CreateUserResponseView.class);
        //when
        HttpHeaders responseHeaders = expectResponse.getResponseHeaders();

        //then
        assertThat(responseHeaders.getLocation()).isNotNull();
    }


    @DisplayName("한번도 로그인 경험이 없는 사용자가 로그인")
    @Test
    public void userLogin(SoftAssertions softly) {
        //when
        EntityExchangeResult<LoginUserResponseView> expectResponse
                = restWebClientTest.postMethodAcceptance(USER_BASE_URL + LOGIN_API_URL, LOGIN_USER, LoginUserResponseView.class);

        LoginUserResponseView responseBody = expectResponse.getResponseBody();

        //then
        softly.assertThat(responseBody.getSocialTokenId()).isNotBlank();
        softly.assertThat(responseBody.getSocialType()).isEqualTo(NAVER_SOCIAL_TYPE);
        softly.assertThat(responseBody.getName()).isNotBlank();
        softly.assertThat(responseBody.getJwt()).isNotBlank();
    }

    @DisplayName("사용자가_로그인한_상태에서_본인_정보를_조회할수_있는지")
    @Test
    public void userDetailWithAuth(SoftAssertions softly) {
        //given
        restWebClientTest.createUser();

        //when
        EntityExchangeResult<FindByIdResponseView> expectResponse
                = restWebClientTest.getMethodWithAuthAcceptance(USER_BASE_URL + "/1", FindByIdResponseView.class, getJwt());

        FindByIdResponseView responseBody = expectResponse.getResponseBody();

        //then
        softly.assertThat(responseBody.getName()).isEqualTo(KIM_NAME);
    }

    private String getJwt() {
        return tokenAuthenticationService.toJwtBySocialTokenId(KIM_EMAIL);
    }
}
