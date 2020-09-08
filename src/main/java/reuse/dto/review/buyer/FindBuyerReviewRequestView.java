package reuse.dto.review.buyer;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reuse.domain.Board;
import reuse.domain.BuyerReview;
import reuse.domain.User;
import reuse.dto.user.FindByIdResponseView;

@Getter
@Setter
@NoArgsConstructor
public class FindBuyerReviewRequestView {
    private FindByIdResponseView buyer;
    private Integer score;
    private String title;
    private String content;
    private Long boardId;
    @Builder
    public FindBuyerReviewRequestView(User buyer, Integer score, String title, String content, Long boardId) {
        this.buyer = FindByIdResponseView.toDto(buyer);
        this.score = score;
        this.title = title;
        this.content = content;
        this.boardId = boardId;
    }


    public static FindBuyerReviewRequestView toDto(BuyerReview buyerReview) {
        return FindBuyerReviewRequestView.builder()
                .buyer(buyerReview.getBuyer())
                .content(buyerReview.getContent())
                .score(buyerReview.getScore())
                .title(buyerReview.getTitle())
                .build();
    }
}
