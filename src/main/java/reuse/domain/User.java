package reuse.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 40)
    private String socialTokenId;

    @Email
    private String email;

    @Size(min = 1, max = 20)
    private String name;

    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Favorite> favorites = new ArrayList<>();

    @Builder
    public User(Long id, String socialTokenId, String email, String name, String password, List<Favorite> favorites) {
        this.id = id;
        this.socialTokenId = socialTokenId;
        this.email = email;
        this.name = name;
        this.password = password;
        this.favorites = favorites;
    }

    public User(Long id, String socialTokenId, String email, String name, String password) {
        this(id, socialTokenId, email, name, password, new ArrayList<>());
    }

    public User(Long id, String email, String name) {
        this(id, email, name, null, new ArrayList<>());
    }

    public static User of(Long id, String socialTokenId, String email, String name, String password) {
        return new User(null, socialTokenId, email, name, password, new ArrayList<>());
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

    public String getPassword() {
        return password;
    }

    public List<Favorite> getFavorites() {
        return favorites;
    }
}
