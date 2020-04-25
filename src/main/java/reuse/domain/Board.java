package reuse.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
public class Board extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 100)
    private String title;

    @Size(min = 1, max = 1000)
    private String content;

    @OneToOne
    @JoinColumn(name="product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User seller;

    private String sellerAddress;

    @Builder
    public Board(Long id, String title, String content, Product product, User seller, String sellerAddress) {
        super(id);
        this.title = title;
        this.content = content;
        this.product = product;
        this.seller = seller;
        this.sellerAddress = sellerAddress;
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
}
