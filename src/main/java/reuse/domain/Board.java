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
    @Size(min = 1, max = 100)
    private String title;

    @Size(min = 1, max = 1000)
    private String content;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name="product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name="seller_id")
    private User seller;

    @ManyToOne
    @JoinColumn(name="buyer_id")
    private User buyer;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name="buyer_review_id")
    private BuyerReview buyerReview;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name="seller_review_id")
    private SellerReview sellerReview;

    private String sellerAddress;

    @Enumerated(EnumType.STRING)
    private SalesStatusType salesStatus = SalesStatusType.SALE;

    private Boolean isDeleted = false;

    @Builder
    public Board(String title, String content, Product product, User seller, String sellerAddress, BuyerReview buyerReview,
                 SellerReview sellerReview, SalesStatusType salesStatus, User buyer) {
        this.title = title;
        this.content = content;
        this.product = product;
        this.seller = seller;
        this.sellerAddress = sellerAddress;
        this.buyerReview = buyerReview;
        this.sellerReview = sellerReview;
        this.salesStatus = salesStatus;
        this.buyer = buyer;
    }

    @Builder
    public Board(Long id, String title, String content, Product product, User seller, String sellerAddress,
                 BuyerReview buyerReview, SellerReview sellerReview, SalesStatusType salesStatus, User buyer) {
        super(id);
        this.title = title;
        this.content = content;
        this.product = product;
        this.seller = seller;
        this.sellerAddress = sellerAddress;
        this.buyerReview = buyerReview;
        this.sellerReview = sellerReview;
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

    public SellerReview getSellerReview() {
        return this.sellerReview;
    }

    @JsonIgnore
    public Long getBuyerId() {
        return this.buyer.getId();
    }

    public Board reserve() {
        verifyThatBoardCanChangeStatus(SalesStatusType.SALE);

        this.salesStatus = SalesStatusType.RESERVE;
        return this;
    }

    public Board complete() {
        verifyThatBoardCanChangeStatus(SalesStatusType.RESERVE);

        this.salesStatus = SalesStatusType.COMPLETE;
        return this;
    }

    public void verifyThatBoardCanChangeStatus(SalesStatusType sale) {
        if (!this.salesStatus.equals(sale)) {
            throw new IllegalArgumentException("현재 상태 :  " + this.salesStatus + " | 요구되는 상태 " + sale);
        }
    }

    public void verifyThatRequestAreTheSame(String requesterSocialTokenId, String socialTokenId) {
        if (!requesterSocialTokenId.equals(socialTokenId)
                && this.salesStatus.equals(SalesStatusType.COMPLETE)) {

            log.error("#### SocialTokenId : " + socialTokenId);
            log.error("#### requester 의 SocialTokenId : " + requesterSocialTokenId);

            throw new IllegalArgumentException("예약 신청한 사용자가 다릅니다.");
        }
    }

    public void addScoreFromBuyerToSeller(Integer score) {
        seller.addScore(score);
    }

    public void addScoreFromSellerToBuyer(Integer score) {
        buyer.addScore(score);
    }

    public Board registerBuyer(User requester) {
        this.buyer = requester;

        return this;
    }

    public Board registerSellerReview(SellerReview sellerReview) {
        this.sellerReview = sellerReview;

        return this;
    }

    public Board registerBuyerReview(BuyerReview buyerReview) {
        this.buyerReview = buyerReview;

        return this;
    }

    public String getBuyerTokenId() {
        return this.buyer.getSocialTokenId();
    }

    public String getSellerTokenId() {
        return this.seller.getSocialTokenId();
    }
}
