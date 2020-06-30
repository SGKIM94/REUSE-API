package reuse.dto.review.buyer;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reuse.domain.Board;
import reuse.domain.BuyerReview;
import reuse.domain.User;

@Getter
@Setter
@NoArgsConstructor
public class FindBuyerReviewRequestView {
    private User buyer;
    private String rating;
    private String title;
    private String content;
    private Board board;

    @Builder
    public FindBuyerReviewRequestView(User buyer, String rating, String title, String content, Board board) {
        this.buyer = buyer;
        this.rating = rating;
        this.title = title;
        this.content = content;
        this.board = board;
    }

    public static FindBuyerReviewRequestView toDto(BuyerReview buyerReview) {
        return FindBuyerReviewRequestView.builder()
                .buyer(buyerReview.getBuyer())
                .content(buyerReview.getContent())
                .rating(buyerReview.getRating())
                .title(buyerReview.getTitle())
                .build();
    }
}
