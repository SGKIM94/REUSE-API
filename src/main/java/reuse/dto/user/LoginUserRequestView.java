package reuse.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserRequestView {
    private String socialTokenId;
    private String password;

    public LoginUserRequestView() {
    }

    public LoginUserRequestView(String socialTokenId, String password) {
        this.socialTokenId = socialTokenId;
        this.password = password;
    }
}
