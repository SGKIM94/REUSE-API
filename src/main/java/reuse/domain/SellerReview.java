package reuse.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
public class SellerReview extends AbstractEntity {
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name="seller_id")
    private User seller;

    private Integer score;

    @Size(min = 1, max = 100)
    private String title;

    @Size(min = 1, max = 1000)
    private String content;

    @Builder
    public SellerReview(User seller, Integer score, String title, String content) {
        this.seller = seller;
        this.score = score;
        this.title = title;
        this.content = content;
    }

    @Builder
    public SellerReview(Long id, User seller, Integer score, String title, String content) {
        super(id);
        this.seller = seller;
        this.score = score;
        this.title = title;
        this.content = content;
    }

    public int getScore() {
        return score;
    }
}
