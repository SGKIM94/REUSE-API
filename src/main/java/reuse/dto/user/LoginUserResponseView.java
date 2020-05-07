package reuse.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reuse.domain.User;

@Getter
@Setter
@NoArgsConstructor
public class LoginUserResponseView {
    private String socialTokenId;
    private String socialType;
    private String jwt;

    @Builder
    public LoginUserResponseView(String socialTokenId, String socialType, String jwt) {
        this.socialTokenId = socialTokenId;
        this.socialType = socialType;
        this.jwt = jwt;
    }

    public static LoginUserResponseView toDto(String jwt, User user) {
        return LoginUserResponseView.builder()
                .socialTokenId(user.getSocialTokenId())
                .socialType(user.getSocialType())
                .jwt(jwt)
                .build();
    }
}
