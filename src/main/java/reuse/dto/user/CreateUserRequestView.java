package reuse.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reuse.domain.User;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserRequestView {
    private Long id;
    private String socialTokenId;
    private String email;
    private String name;

    public CreateUserRequestView(Long id, String socialTokenId, String email, String name) {
        this.id = id;
        this.socialTokenId = socialTokenId;
        this.email = email;
        this.name = name;
    }

    public static User toEntity(CreateUserRequestView newUser) {
        return new User();
    }
}
