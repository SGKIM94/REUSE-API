package reuse.dto.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reuse.domain.Board;
import reuse.domain.SalesStatusType;
import reuse.dto.product.FindProductResponseView;
import reuse.dto.review.buyer.FindBuyerReviewResponseView;
import reuse.dto.review.seller.FindSellerReviewResponseView;
import reuse.dto.user.FindByIdResponseView;

@Getter
@Setter
@NoArgsConstructor
public class FindBoardResponseView {
    private Long id;
    private String title;
    private String content;
    private FindProductResponseView product;
    private FindByIdResponseView seller;
    private String sellerAddress;
    private SalesStatusType salesStatus;
    private FindBuyerReviewResponseView buyerReview;
    private FindSellerReviewResponseView sellerReview;

    @Builder
    public FindBoardResponseView(Long id, String title, String content, FindProductResponseView product, FindByIdResponseView seller,
                                 String sellerAddress, SalesStatusType salesStatus,
                                 FindBuyerReviewResponseView buyerReview, FindSellerReviewResponseView sellerReview) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.product = product;
        this.seller = seller;
        this.sellerAddress = sellerAddress;
        this.salesStatus = salesStatus;
        this.buyerReview = buyerReview;
        this.sellerReview = sellerReview;
    }

    public static FindBoardResponseView toDto(Board board) {
        return FindBoardResponseView.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .product(FindProductResponseView.toDto(board.getProduct()))
                .seller(FindByIdResponseView.toDto(board.getSeller()))
                .sellerAddress(board.getSellerAddress())
                .salesStatus(board.getSalesStatus())
                .buyerReview(FindBuyerReviewResponseView.toDto(board.getBuyerReview()))
                .sellerReview(FindSellerReviewResponseView.toDto(board.getSellerReview()))
                .build();
    }
}
