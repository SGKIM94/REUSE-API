package reuse.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reuse.domain.User;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserRequestView {
    private String socialTokenId;
    private String socialType;
    private String name;

    @Builder
    public CreateUserRequestView(String socialTokenId, String name) {
        this.socialTokenId = socialTokenId;
        this.name = name;
    }

    public static User toEntity(CreateUserRequestView newUser) {
        return User.builder()
                .socialType(newUser.getSocialType())
                .socialTokenId(newUser.getSocialTokenId())
                .name(newUser.getName())
                .build();
    }
}
