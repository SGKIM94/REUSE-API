package reuse.dto.review.buyer;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ListBuyerReviewRequestView {
    private List<FindBuyerReviewRequestView> buyerReview;

    @Builder
    public ListBuyerReviewRequestView(List<FindBuyerReviewRequestView> buyerReview) {
        this.buyerReview = buyerReview;
    }

    public int getSize() {
        return this.buyerReview.size();
    }

    public static ListBuyerReviewRequestView toDto(List<FindBuyerReviewRequestView> buyerReview) {
        return ListBuyerReviewRequestView.builder()
                .buyerReview(buyerReview)
                .build();
    }
}
