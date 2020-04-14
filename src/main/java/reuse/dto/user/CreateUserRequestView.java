package reuse.dto.user;

import reuse.domain.User;

public class CreateUserRequestView {
    private Long id;
    private String socialTokenId;
    private String email;
    private String password;
    private String name;

    public CreateUserRequestView() {
    }

    public CreateUserRequestView(Long id, String socialTokenId, String email, String password, String name) {
        this.id = id;
        this.socialTokenId = socialTokenId;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public static User toEntity(CreateUserRequestView newUser) {
        return new User();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
