package reuse.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Product extends AbstractEntity {
    @Size(max = 20, message = "이름은 최대 20자리입니다.")
    private String name;

    @Size(max = 200, message = "품목 설명은 최대 200자리입니다.")
    private String explanation;

    @Column(nullable = false)
    private String price;

    private String tax = "0";

    @Column(nullable = false)
    private Boolean isUsed;

    @Column(nullable = false)
    private Boolean isSold;

    private String quality;

    @Embedded
    private ProductImages productImages;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @Builder
    public Product(String name, String explanation, String price, String tax, Boolean isUsed, Boolean isSold,
                   Category category, String quality, ProductImages productImages) {
        this.name = name;
        this.explanation = explanation;
        this.price = price;
        this.tax = tax;
        this.isUsed = isUsed;
        this.isSold = isSold;
        this.category = category;
        this.quality = quality;
        this.productImages = productImages;
    }

    @Builder(builderMethodName = "testBuilder")
    public Product(Long id, String name, String explanation, String price, String tax, Boolean isUsed, Boolean isSold,
                   Category category, String quality, ProductImages productImages) {
        super(id);
        this.name = name;
        this.explanation = explanation;
        this.price = price;
        this.tax = tax;
        this.isUsed = isUsed;
        this.isSold = isSold;
        this.category = category;
        this.quality = quality;
        this.productImages = productImages;
    }

    public String retrieveMainImage() {
        return this.productImages.retrieveMainImage();
    }

    public List<String> retrieveImages() {
        return this.productImages.retrieveImages();
    }

}
