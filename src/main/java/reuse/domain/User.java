package reuse.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @Size(min = 1, max = 20)
    private String name;

    @Size(min = 1, max = 20)
    private String socialType;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Favorite> favorites = new ArrayList<>();

    @Builder
    public User(Long id, String socialTokenId, String name, String socialType, List<Favorite> favorites) {
        this.id = id;
        this.socialTokenId = socialTokenId;
        this.name = name;
        this.socialType = socialType;
        this.favorites = favorites;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSocialTokenId() {
        return socialTokenId;
    }
}
