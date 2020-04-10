package reuse.fixture;

import reuse.domain.User;
import reuse.dto.user.CreateUserRequestView;
import reuse.dto.user.FindByEmailResponseView;
import reuse.dto.user.LoginUserRequestView;

public class UserFixture {
    public static final Long KIM_ID = 1L;
    public static final String KIM_NAME = "김상구";
    public static final String KIM_EMAIL = "sgkim94@github.com";
    public static final String KIM_PASSWORD = "password";
    public static final CreateUserRequestView USER_SIGH_UP_REQUEST_DTO = new CreateUserRequestView(1L, KIM_EMAIL, KIM_NAME, KIM_PASSWORD);
    public static final LoginUserRequestView USER_LOGIN_REQUEST_DTO = new LoginUserRequestView(KIM_EMAIL, KIM_PASSWORD);
    public static final FindByEmailResponseView FIND_BY_EMAIL_RESPONSE_VIEW = new FindByEmailResponseView(KIM_ID, KIM_EMAIL, KIM_PASSWORD);

    public static CreateUserRequestView getCreateUserRequestView(User user) {
        return new CreateUserRequestView(1L, user.getEmail(), user.getPassword(), user.getName());
    }

    public static LoginUserRequestView getLoginUserRequestView(User user) {
        return new LoginUserRequestView(user.getEmail(), user.getPassword());
    }

    public static final User TEST_USER = new User(KIM_ID, KIM_EMAIL, KIM_NAME, KIM_PASSWORD);
    public static final User LOGIN_USER = User.builder().email(KIM_EMAIL).password(KIM_PASSWORD).build();
}
