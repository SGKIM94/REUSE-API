package reuse.dto.review.buyer;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import reuse.domain.BuyerReview;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ListBuyerReviewResponseView {
    private List<FindBuyerReviewRequestView> buyerReview;

    @Builder
    public ListBuyerReviewResponseView(List<FindBuyerReviewRequestView> buyerReview) {
        this.buyerReview = buyerReview;
    }

    public int getSize() {
        return this.buyerReview.size();
    }

    public static ListBuyerReviewResponseView toDto(List<FindBuyerReviewRequestView> buyerReview) {
        return ListBuyerReviewResponseView.builder()
                .buyerReview(buyerReview)
                .build();
    }

    public static ListBuyerReviewResponseView toDtoByEntity(List<BuyerReview> buyerReview) {
        List<FindBuyerReviewRequestView> buyerReviews = buyerReview.stream()
                .map(FindBuyerReviewRequestView::toDto)
                .collect(Collectors.toList());

        return ListBuyerReviewResponseView.toDto(buyerReviews);
    }
}
