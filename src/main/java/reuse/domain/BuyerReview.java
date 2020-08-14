package reuse.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
public class BuyerReview extends AbstractEntity {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="buyer_id")
    private User buyer;

    private Integer score;

    @Size(min = 1, max = 100)
    private String title;

    @Size(min = 1, max = 1000)
    private String content;

    @Builder
    public BuyerReview(User buyer, Integer score, String title, String content) {
        this.buyer = buyer;
        this.score = score;
        this.title = title;
        this.content = content;
    }

    @Builder(builderMethodName = "TestBuilder")
    public BuyerReview(Long id, User buyer, Integer score, String title, String content) {
        super(id);
        this.buyer = buyer;
        this.score = score;
        this.title = title;
        this.content = content;
    }

    public User getBuyer() {
        return buyer;
    }

    public int getScore() {
        return score;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public BuyerReview modify(BuyerReview buyerReview) {
        if (buyerReview == null) {
            return this;
        }

        this.score = buyerReview.getScore();
        this.title = buyerReview.getTitle();
        this.content = buyerReview.getContent();

        return this;
    }
}
