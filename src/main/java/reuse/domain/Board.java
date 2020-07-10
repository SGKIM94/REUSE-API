package reuse.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reuse.dto.board.ModifyBoardRequestView;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
@Slf4j
public class Board extends AbstractEntity {
    // TODO: 일급 콜렉션으로 추출 필요
    public enum SalesStatusType {
        SALE, RESERVE, COMPLETE, STOP
    }

    @Size(min = 1, max = 100)
    private String title;

    @Size(min = 1, max = 1000)
    private String content;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name="seller_id")
    private User seller;

    @ManyToOne
    @JoinColumn(name="buyer_id")
    private User buyer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="buyer_review_id")
    private BuyerReview buyerReview;

    private String sellerAddress;

    @Enumerated(EnumType.ORDINAL)
    private SalesStatusType salesStatus;

    private Boolean isDeleted = false;

    @Builder
    public Board(String title, String content, Product product, User seller, String sellerAddress, BuyerReview buyerReview, SalesStatusType salesStatus, User buyer) {
        this.title = title;
        this.content = content;
        this.product = product;
        this.seller = seller;
        this.sellerAddress = sellerAddress;
        this.buyerReview = buyerReview;
        this.salesStatus = salesStatus;
        this.buyer = buyer;
    }

    @Builder
    public Board(Long id, String title, String content, Product product, User seller, String sellerAddress, BuyerReview buyerReview, SalesStatusType salesStatus, User buyer) {
        super(id);
        this.title = title;
        this.content = content;
        this.product = product;
        this.seller = seller;
        this.sellerAddress = sellerAddress;
        this.buyerReview = buyerReview;
        this.salesStatus = salesStatus;
        this.buyer = buyer;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Product getProduct() {
        return product;
    }

    public User getSeller() {
        return seller;
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public SalesStatusType getSalesStatus() {
        return salesStatus;
    }

    public Board modify(ModifyBoardRequestView modify) {
        if (modify == null) {
            return this;
        }

        this.title = modify.getTitle();
        this.content = modify.getContent();
        this.product = modify.getProduct();
        this.sellerAddress = modify.getSellerAddress();

        return this;
    }

    public void mappingBuyerReview(BuyerReview buyerReview) {
        if (buyerReview == null) {
            throw new IllegalArgumentException("Empty buyerReview when mapping to the Board!");
        }

        this.buyerReview = buyerReview;
    }

    @JsonIgnore
    public long getBuyerReviewId() {
        return this.buyerReview.getId();
    }

    public void delete() {
        this.isDeleted = true;
    }

    public BuyerReview getBuyerReview() {
        return this.buyerReview;
    }

    public boolean isCompleteSalesStatus() {
        return SalesStatusType.COMPLETE.equals(this.salesStatus);
    }

    @JsonIgnore
    public Long getBuyerId() {
        return this.buyer.getId();
    }

    public Board reserve(User requester) {
        verifyThatUserAndRequester(requester, SalesStatusType.SALE);

        this.salesStatus = SalesStatusType.RESERVE;
        return this;
    }

    public Board complete(User requester) {
        verifyThatUserAndRequester(requester, SalesStatusType.RESERVE);

        this.salesStatus = SalesStatusType.COMPLETE;
        return this;
    }

    void verifyThatUserAndRequester(User requester, SalesStatusType requiredStatus) {
        String requesterSocialTokenId = requester.getSocialTokenId();
        String boardSellerSocialTokenId = this.seller.getSocialTokenId();

        verifyThatSellerAndRequestAretheSame(requesterSocialTokenId, boardSellerSocialTokenId);
        verifyThatBoardCanChangeStatus(requiredStatus);
    }

    void verifyThatBoardCanChangeStatus(SalesStatusType sale) {
        if (!this.salesStatus.equals(sale)) {
            throw new IllegalArgumentException("현재 " + this.salesStatus + " 상태이므로 " + sale + " 상태로 변경이 불가능합니다.");
        }
    }

    private void verifyThatSellerAndRequestAretheSame(String requesterSocialTokenId, String boardSellerSocialTokenId) {
        if (!requesterSocialTokenId.equals(boardSellerSocialTokenId)) {
            log.error("#### seller 의 SocialTokenId : " + boardSellerSocialTokenId);
            log.error("#### User 의 SocialTokenId : " + requesterSocialTokenId);
            throw new IllegalArgumentException("판매자와 예약 신청한 사용자가 다릅니다.");
        }
    }
}
