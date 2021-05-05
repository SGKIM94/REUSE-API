package reuse.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import reuse.domain.User;

@Getter
@NoArgsConstructor
public class CreateUserRequestView {
    private String socialTokenId;
    private String socialType;
    private String name;

    @Builder
    public CreateUserRequestView(String socialTokenId, String socialType, String name) {
        this.socialTokenId = socialTokenId;
        this.socialType = socialType;
        this.name = name;
    }

    public static User toEntity(CreateUserRequestView newUser) {
        return User.builder()
                .socialType(newUser.getSocialType())
                .socialTokenId(newUser.getSocialTokenId())
                .name(newUser.getName())
                .score(0)
                .build();
    }
}
