package reuse.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
@Getter
public class Product extends AbstractEntity {
    @Size(min = 1, max = 20)
    private String name;

    @Size(min = 1, max = 200)
    private String explanation;

    @Column(nullable = false)
    private String price;

    @Column(nullable = false)
    private String tax;

    @Column(nullable = false)
    private Boolean isUsed;

    @Column(nullable = false)
    private Boolean isSold;

    private String categoryId;

    private String productThumbnailImage;

    @OneToOne
    @JoinColumn(name = "product_images_id")
    private ProductImages productImages;

    @Builder
    public Product(long id, String name, String explanation, String price, String tax, Boolean isUsed, Boolean isSold,
                   String categoryId, String productThumbnailImage, ProductImages productImages) {
        super(id);
        this.name = name;
        this.explanation = explanation;
        this.price = price;
        this.tax = tax;
        this.isUsed = isUsed;
        this.isSold = isSold;
        this.categoryId = categoryId;
        this.productThumbnailImage = productThumbnailImage;
        this.productImages = productImages;
    }
}
