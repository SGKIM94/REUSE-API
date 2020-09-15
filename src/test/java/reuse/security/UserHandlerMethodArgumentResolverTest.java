package reuse.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import reuse.domain.User;

import javax.servlet.http.HttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static reuse.fixture.UserFixture.TEST_USER;
import static reuse.security.JwtAuthInterceptor.AUTH_USER_KEY;

@ExtendWith(MockitoExtension.class)
public class UserHandlerMethodArgumentResolverTest {
    @Mock
    private MethodParameter parameter;

    @Mock
    private NativeWebRequest request;

    @Mock
    private LoginUser annotedLoginUser;

    @Mock
    private HttpServletRequest httpServletRequest;

    private UserHandlerMethodArgumentResolver userHandlerMethodArgumentResolver;

    @BeforeEach
    public void setup() {

        userHandlerMethodArgumentResolver = new UserHandlerMethodArgumentResolver();
    }

    @Test
    public void 사용자정보가_파라미터로_넘어가_정상처리되는경우() {
        when(parameter.getParameterAnnotation(LoginUser.class)).thenReturn(annotedLoginUser);
        when(request.getNativeRequest()).thenReturn(httpServletRequest);
        when(httpServletRequest.getAttribute(AUTH_USER_KEY)).thenReturn(TEST_USER);

        User loginUser
                = (User) userHandlerMethodArgumentResolver.resolveArgument(parameter, null, request, null);

        assertThat(loginUser).isEqualTo(TEST_USER);
    }

    @Test
    public void 사용자정보가_존재하지_않는경우() {
        when(parameter.getParameterAnnotation(LoginUser.class)).thenReturn(annotedLoginUser);
        when(request.getNativeRequest()).thenReturn(httpServletRequest);
        when(httpServletRequest.getAttribute(AUTH_USER_KEY)).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> {
            userHandlerMethodArgumentResolver.resolveArgument(parameter, null, request, null);
        });
    }
}
