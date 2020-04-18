package reuse.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reuse.domain.User;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserResponseView {
    private Long id;
    private String socialTokenId;
    private String socialType;
    private String name;

    @Builder
    public CreateUserResponseView(Long id, String socialTokenId, String socialType, String name) {
        this.id = id;
        this.socialTokenId = socialTokenId;
        this.socialType = socialType;
        this.name = name;
    }

    public static CreateUserResponseView toDto(User user) {
        return CreateUserResponseView.builder()
                .socialTokenId(user.getSocialTokenId())
                .socialType(user.getSocialType())
                .name(user.getName())
                .build();
    }
}
