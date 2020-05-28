package reuse.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    private String tax = "0";

    @Column(nullable = false)
    private Boolean isUsed;

    @Column(nullable = false)
    private Boolean isSold;

    private String categoryId;

    private String thumbnailImage;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_images_id")
    private ProductImages productImages;

    @Builder
    public Product(String name, String explanation, String price, String tax, Boolean isUsed, Boolean isSold,
                   String categoryId, String thumbnailImage, ProductImages productImages) {
        this.name = name;
        this.explanation = explanation;
        this.price = price;
        this.tax = tax;
        this.isUsed = isUsed;
        this.isSold = isSold;
        this.categoryId = categoryId;
        this.thumbnailImage = thumbnailImage;
        this.productImages = productImages;
    }
}
