package reuse.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;
import org.apache.commons.text.RandomStringGenerator;

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
    private List<FavoriteBoard> favoriteBoards = new ArrayList<>();

    private Integer score;

    @Builder
    public User(String socialTokenId, String name, String socialType, List<FavoriteBoard> favoriteBoards, Integer score) {
        this.socialTokenId = socialTokenId;
        this.name = name;
        this.socialType = socialType;
        this.favoriteBoards = favoriteBoards;
        this.score = score;
    }

    public User(String socialTokenId, String name, String socialType) {
        this.socialTokenId = socialTokenId;
        this.name = name;
        this.socialType = socialType;
        this.favoriteBoards = new ArrayList<>();
        this.score = 0;
    }

    public User(Long id, String socialTokenId, String name, String socialType) {
        super(id);
        this.socialTokenId = socialTokenId;
        this.name = name;
        this.socialType = socialType;
        this.favoriteBoards = new ArrayList<>();
        this.score = 0;
    }

    public static String getRandomUserName() {
        return new RandomStringGenerator.Builder()
                .withinRange('a', 'z')
                .build()
                .generate(10);
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

    public Integer getScore() {
        return score;
    }

    public int addScore(Integer score) {
        this.score += score;
        return score;
    }
}
