package reuse.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
public class SellerReview extends AbstractEntity {
    @OneToOne
    @JoinColumn(name="seller_id")
    private User seller;

    private int score;

    @Size(min = 1, max = 100)
    private String title;

    @Size(min = 1, max = 1000)
    private String content;

    @Builder
    public SellerReview(User seller, int score, String title, String content) {
        this.seller = seller;
        this.score = score;
        this.title = title;
        this.content = content;
    }

    @Builder
    public SellerReview(Long id, User seller, int score, String title, String content) {
        super(id);
        this.seller = seller;
        this.score = score;
        this.title = title;
        this.content = content;
    }
}
