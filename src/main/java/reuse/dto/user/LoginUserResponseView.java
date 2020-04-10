package reuse.dto.user;

public class LoginUserResponseView {
    private String accessToken;
    private String tokenType;

    public LoginUserResponseView() {
    }

    public LoginUserResponseView(String accessToken, String tokenType) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
    }

    public static LoginUserResponseView toDto(String accessToken, String tokenType) {
        return new LoginUserResponseView(accessToken, tokenType);
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }
}
