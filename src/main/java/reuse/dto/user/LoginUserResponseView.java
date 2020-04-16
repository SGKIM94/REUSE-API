package reuse.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginUserResponseView {
    private String socialTokenId;
    private String socialType;

    @Builder
    public LoginUserResponseView(String socialTokenId, String socialType) {
        this.socialTokenId = socialTokenId;
        this.socialType = socialType;
    }

    public static LoginUserResponseView toDto(String jwt, String tokenTypeByJwt) {
        return LoginUserResponseView.builder()
                .socialTokenId(jwt)
                .socialType(tokenTypeByJwt)
                .build();
    }
}
