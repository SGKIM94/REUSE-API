package reuse.dto.review.buyer;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reuse.domain.Board;
import reuse.domain.BuyerReview;
import reuse.dto.user.FindByIdResponseView;

@Getter
@Setter
@NoArgsConstructor
public class FindBuyerReviewRequestView {
    private FindByIdResponseView buyer;
    private Integer score;
    private String title;
    private String content;
    private Board board;

    @Builder
    public FindBuyerReviewRequestView(FindByIdResponseView buyer, Integer score, String title, String content, Board board) {
        this.buyer = buyer;
        this.score = score;
        this.title = title;
        this.content = content;
        this.board = board;
    }

    public static FindBuyerReviewRequestView toDto(BuyerReview buyerReview) {
        return FindBuyerReviewRequestView.builder()
                .buyer(FindByIdResponseView.toDto(buyerReview.getBuyer()))
                .content(buyerReview.getContent())
                .score(buyerReview.getScore())
                .title(buyerReview.getTitle())
                .build();
    }
}
