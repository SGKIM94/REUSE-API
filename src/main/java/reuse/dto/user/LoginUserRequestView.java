package reuse.dto.user;

public class LoginUserRequestView {
    private String email;
    private String password;

    public LoginUserRequestView() {
    }

    public LoginUserRequestView(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
