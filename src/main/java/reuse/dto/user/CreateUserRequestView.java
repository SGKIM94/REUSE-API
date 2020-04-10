package reuse.dto.user;

import reuse.domain.User;

public class CreateUserRequestView {
    private Long id;
    private String email;
    private String password;
    private String name;

    public CreateUserRequestView() {
    }

    public CreateUserRequestView(Long id, String email, String password, String name) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public static User toEntity(CreateUserRequestView newUser) {
        return new User();
    }

    public User toUser() {
        return new User(id, email, password, name);
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}
