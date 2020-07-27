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
    private Integer score;
    private User buyer;
    private Long boardId;

    @Builder
    public CreateBuyerReviewRequestView(String title, String content, Integer score, Long boardId, User buyer) {
        this.title = title;
        this.content = content;
        this.score = score;
        this.boardId = boardId;
        this.buyer = buyer;
    }

    public BuyerReview toEntity(User buyer) {
        return BuyerReview.builder()
                .content(content)
                .score(score)
                .title(title)
                .buyer(buyer)
                .build();
    }
}
