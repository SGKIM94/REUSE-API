package reuse.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reuse.domain.User;

@Getter
@Setter
@NoArgsConstructor
public class LoginUserRequestView {
    private Long id;
    private String socialTokenId;
    private String socialType;

    @Builder
    public LoginUserRequestView(Long id, String socialTokenId, String socialType) {
        this.id = id;
        this.socialTokenId = socialTokenId;
        this.socialType = socialType;
    }

    public static User toEntity(LoginUserRequestView newUser) {
        return User.builder()
                .id(newUser.getId())
                .socialTokenId(newUser.getSocialTokenId())
                .socialType(newUser.getSocialType())
                .build();
    }
}
