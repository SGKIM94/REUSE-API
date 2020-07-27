package reuse.dto.review.seller;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reuse.domain.SellerReview;
import reuse.domain.User;

@Getter
@Setter
@NoArgsConstructor
public class CreateSellerReviewRequestView {
    private String title;
    private String content;
    private Integer score;
    private User seller;
    private Long boardId;

    @Builder
    public CreateSellerReviewRequestView(String title, String content, Integer score, Long boardId, User seller) {
        this.title = title;
        this.content = content;
        this.score = score;
        this.boardId = boardId;
        this.seller = seller;
    }

    public SellerReview toEntity() {
        return SellerReview.builder()
                .content(this.content)
                .score(this.score)
                .seller(this.seller)
                .title(this.title)
                .build();
    }
}
