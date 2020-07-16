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
    private String rating;
    private User seller;
    private Long boardId;

    @Builder
    public CreateSellerReviewRequestView(String title, String content, String rating, Long boardId, User seller) {
        this.title = title;
        this.content = content;
        this.rating = rating;
        this.boardId = boardId;
        this.seller = seller;
    }

    public SellerReview toEntity() {
        return SellerReview.builder()
                .content(this.content)
                .rating(this.rating)
                .seller(this.seller)
                .title(this.title)
                .build();
    }
}
