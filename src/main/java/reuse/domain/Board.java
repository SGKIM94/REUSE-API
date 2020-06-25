package reuse.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;
import reuse.dto.board.ModifyBoardRequestView;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
public class Board extends AbstractEntity {
    @Size(min = 1, max = 100)
    private String title;

    @Size(min = 1, max = 1000)
    private String content;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User seller;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="buyer_review_id")
    private BuyerReview buyerReview;

    private String sellerAddress;

    private Boolean isDeleted = false;

    @Builder
    public Board(String title, String content, Product product, User seller, String sellerAddress, BuyerReview buyerReview) {
        this.title = title;
        this.content = content;
        this.product = product;
        this.seller = seller;
        this.sellerAddress = sellerAddress;
        this.buyerReview = buyerReview;
    }

    @Builder
    public Board(Long id, String title, String content, Product product, User seller, String sellerAddress, BuyerReview buyerReview) {
        super(id);
        this.title = title;
        this.content = content;
        this.product = product;
        this.seller = seller;
        this.sellerAddress = sellerAddress;
        this.buyerReview = buyerReview;
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

    public void modify(ModifyBoardRequestView modify) {
        if (modify == null) {
            return;
        }

        this.title = modify.getTitle();
        this.content = modify.getContent();
        this.product = modify.getProduct();
        this.sellerAddress = modify.getSellerAddress();
    }

    public void mappingBuyerReview(BuyerReview buyerReview) {
        if (buyerReview == null) {
            throw new IllegalArgumentException("Empty buyerReview when mapping to the Board!");
        }

        this.buyerReview = buyerReview;
    }

    public long getBuyerReviewId() {
        return this.buyerReview.getId();
    }

    public void delete() {
        this.isDeleted = true;
    }

    public BuyerReview getBuyerReview() {
        return this.buyerReview;
    }
}
