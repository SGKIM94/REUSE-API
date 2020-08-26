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
public class FindSellerReviewResponseView {
    private Long id;
    private User seller;
    private Integer score;
    private String title;
    private String content;

    @Builder
    public FindSellerReviewResponseView(Long id, User seller, Integer score, String title, String content) {
        this.id = id;
        this.seller = seller;
        this.score = score;
        this.title = title;
        this.content = content;
    }

    public static FindSellerReviewResponseView toDto(SellerReview sellerReview) {
        if (sellerReview == null) {
            return new FindSellerReviewResponseView();
        }

        return FindSellerReviewResponseView.builder()
                .id(sellerReview.getId())
                .seller(sellerReview.getSeller())
                .score(sellerReview.getScore())
                .title(sellerReview.getTitle())
                .content(sellerReview.getContent())
                .build();
    }
}
