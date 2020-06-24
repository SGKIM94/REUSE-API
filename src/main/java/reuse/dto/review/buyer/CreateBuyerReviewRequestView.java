package reuse.dto.review.buyer;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reuse.domain.BuyerReview;
import reuse.domain.User;

@Getter
@Setter
@NoArgsConstructor
public class CreateBuyerReviewRequestView {
    private String title;
    private String content;
    private String rating;
    private Long boardId;

    @Builder
    public CreateBuyerReviewRequestView(String title, String content, String rating, Long boardId) {
        this.title = title;
        this.content = content;
        this.rating = rating;
        this.boardId = boardId;
    }

    public BuyerReview toEntity(User buyer) {
        return BuyerReview.builder()
                .content(content)
                .rating(rating)
                .title(title)
                .buyer(buyer)
                .build();
    }
}
