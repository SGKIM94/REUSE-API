package reuse.dto.user;

import reuse.domain.User;


public class CreateUserResponseView {
    private Long id;
    private String email;
    private String password;
    private String name;

    public CreateUserResponseView() {
    }

    public CreateUserResponseView(Long id, String email, String password, String name) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public static CreateUserResponseView toDto(User user) {
        return new CreateUserResponseView(user.getId(), user.getEmail(), user.getPassword(), user.getName());
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
