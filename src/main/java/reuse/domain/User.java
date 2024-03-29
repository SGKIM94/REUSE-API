package reuse.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;
import org.apache.commons.text.RandomStringGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class User extends AbstractEntity {

    private static final int NICK_NAME_SIZE = 10;

    @Size(min = 1, max = 40)
    @Column(unique = true)
    private String socialTokenId;

    @Size(min = 1, max = 20)
    private String name;

    @Size(min = 1, max = 20)
    private String socialType;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
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
                .generate(NICK_NAME_SIZE);
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
