package reuse.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import reuse.domain.User;

@Getter
@Setter
public class LoginUserRequestView {
    private String socialTokenId;
    private String socialType;

    @Builder
    public LoginUserRequestView(String socialTokenId, String socialType) {
        this.socialTokenId = socialTokenId;
        this.socialType = socialType;
    }

    public static User toEntity(LoginUserRequestView newUser) {
        return User.builder()
                .socialTokenId(newUser.socialTokenId)
                .socialType(newUser.socialType)
                .build();
    }
}
