package reuse.dto.review.buyer;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import reuse.domain.BuyerReview;
import reuse.dto.user.FindByIdResponseView;

@Getter
@NoArgsConstructor
public class FindBuyerReviewResponseView {
    private Long id;
    private FindByIdResponseView buyer;
    private Integer score;
    private String title;
    private String content;

    @Builder
    public FindBuyerReviewResponseView(Long id, FindByIdResponseView buyer, Integer score, String title, String content) {
        this.id = id;
        this.buyer = buyer;
        this.score = score;
        this.title = title;
        this.content = content;
    }

    public static FindBuyerReviewResponseView toDto(BuyerReview buyerReview) {
        if (buyerReview == null) {
            return new FindBuyerReviewResponseView();
        }

        return FindBuyerReviewResponseView.builder()
                .id(buyerReview.getId())
                .buyer(FindByIdResponseView.toDto(buyerReview.getBuyer()))
                .score(buyerReview.getScore())
                .title(buyerReview.getTitle())
                .content(buyerReview.getContent())
                .build();
    }
}
