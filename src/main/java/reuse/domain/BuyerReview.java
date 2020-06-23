package reuse.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
public class BuyerReview extends AbstractEntity {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="buyer_id")
    private User buyer;

    private String rating;

    @Size(min = 1, max = 100)
    private String title;

    @Size(min = 1, max = 1000)
    private String content;

    @Builder
    public BuyerReview(User buyer, Board board, String rating, String title, String content) {
        this.buyer = buyer;
        this.rating = rating;
        this.title = title;
        this.content = content;
    }

    public BuyerReview(Long id, Board board, User buyer, String rating, String title, String content) {
        super(id);
        this.buyer = buyer;
        this.rating = rating;
        this.title = title;
        this.content = content;
    }
}
