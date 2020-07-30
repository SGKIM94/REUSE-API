package reuse.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reuse.domain.User;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class LoginUserRequestView {
    private Long id;
    @NotBlank(message = "socialTokenId 는 필수 값입니다.")
    private String socialTokenId;
    @NotBlank(message = "socialType 은 필수 값입니다.")
    private String socialType;

    @Builder
    public LoginUserRequestView(Long id, String socialTokenId, String socialType) {
        this.id = id;
        this.socialTokenId = socialTokenId;
        this.socialType = socialType;
    }

    public static User toEntity(LoginUserRequestView newUser, String randomName) {
        return User.builder()
                .socialTokenId(newUser.getSocialTokenId())
                .socialType(newUser.getSocialType())
                .name(randomName)
                .score(0)
                .build();
    }
}
