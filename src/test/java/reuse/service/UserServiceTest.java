package reuse.service;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reuse.dto.user.CreateUserResponseView;
import reuse.dto.user.FindByEmailResponseView;
import reuse.dto.user.LoginUserResponseView;
import reuse.exception.ExistUserException;
import reuse.repository.UserRepository;
import reuse.security.TokenAuthenticationService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static reuse.fixture.UserFixture.*;
import static reuse.security.TokenAuthenticationService.BEARER_TOKEN_TYPE;

@SpringBootTest
public class UserServiceTest {
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private TokenAuthenticationService tokenAuthenticationService;

    @BeforeEach
    void setUp() {
        this.tokenAuthenticationService = new TokenAuthenticationService();
        this.userService = new UserService(tokenAuthenticationService, userRepository);
    }

    @DisplayName("회원이 로그인이 성공하여 토큰을 리턴하는지")
    @Test
    public void login(SoftAssertions softly) {
        when(userRepository.findBySocialTokenId(any())).thenReturn(FIND_BY_SOCIAL_TOKEN_ID_RESPONSE_VIEW);

        LoginUserResponseView user = userService.login(USER_LOGIN_REQUEST_DTO);

        softly.assertThat(user.getSocialTokenId()).isNotNull();
        softly.assertThat(user.getSocialType()).isEqualTo(NAVER_SOCIAL_TYPE);
    }

    @DisplayName("회원의 ID 로 상세정보가 되는지")
    @Test
    public void findById(SoftAssertions softly) {
        when(userRepository.findById(any())).thenReturn(java.util.Optional.of(TEST_USER));

        FindByEmailResponseView user = userService.findById(KIM_ID);

        softly.assertThat(user.getEmail()).isEqualTo(KIM_EMAIL);
        softly.assertThat(user.getName()).isEqualTo(KIM_NAME);
    }

    @DisplayName("JWT 를 만들어 LoginUserResponseView 에 담아 만드는지")
    @Test
    public void toDtoWithJwt(SoftAssertions softly) {
        LoginUserResponseView loginUserResponseView = userService.toDtoWithJWt(SOCIAL_TOKEN_ID);

        softly.assertThat(loginUserResponseView.getSocialTokenId()).isEqualTo(SOCIAL_TOKEN_ID);
        softly.assertThat(loginUserResponseView.getSocialType()).isEqualTo(NAVER_SOCIAL_TYPE);
    }
}
