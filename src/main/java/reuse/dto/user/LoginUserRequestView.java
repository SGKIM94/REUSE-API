package reuse.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserRequestView {
    private String socialTokenId;

    public LoginUserRequestView() {
    }

    public LoginUserRequestView(String socialTokenId) {
        this.socialTokenId = socialTokenId;
    }
}
