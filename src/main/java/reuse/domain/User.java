package reuse.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class User extends AbstractEntity {
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
        super(id);
        this.socialTokenId = socialTokenId;
        this.name = name;
        this.socialType = socialType;
        this.favorites = favorites;
    }

    public User(Long id, String socialTokenId, String name, String socialType) {
        super(id);
        this.socialTokenId = socialTokenId;
        this.name = name;
        this.socialType = socialType;
        this.favorites = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getSocialTokenId() {
        return socialTokenId;
    }

    public String getSocialType() {
        return socialType;
    }
}
