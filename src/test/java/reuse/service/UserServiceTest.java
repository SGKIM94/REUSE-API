package reuse.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reuse.dto.user.FindByIdResponseView;
import reuse.dto.user.LoginUserResponseView;
import reuse.repository.UserRepository;
import reuse.security.TokenAuthenticationService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static reuse.fixture.UserFixture.*;

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
    public void login() {
        when(userRepository.findBySocialTokenId(any())).thenReturn(TEST_USER);

        LoginUserResponseView user = userService.login(USER_LOGIN_REQUEST_DTO);

        assertThat(user.getSocialTokenId()).isNotBlank();
        assertThat(user.getSocialType()).isEqualTo(NAVER_SOCIAL_TYPE);
        assertThat(user.getJwt()).isNotBlank();
    }

    @DisplayName("회원의 ID 로 상세정보가 되는지")
    @Test
    public void findById() {
        when(userRepository.findById(any())).thenReturn(java.util.Optional.of(TEST_USER));

        FindByIdResponseView user = userService.findById(KIM_ID);

//        assertThat(user.getName()).isEqualTo(KIM_NAME);
    }

    @DisplayName("JWT 를 만들어 LoginUserResponseView 에 담아 만드는지")
    @Test
    public void toDtoWithJwt() {
        LoginUserResponseView loginUserResponseView = userService.toDtoWithJWt(TEST_USER);

        assertThat(loginUserResponseView.getSocialTokenId()).isNotBlank();
        assertThat(loginUserResponseView.getSocialType()).isEqualTo(NAVER_SOCIAL_TYPE);
    }
}
