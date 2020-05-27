package reuse.dto.product;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reuse.domain.Product;
import reuse.domain.ProductImages;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FindProductResponseView {
    private Long id;
    private String name;
    private String explanation;
    private String price;
    private String tax;
    private Boolean isUsed;
    private Boolean isSold;
    private String categoryId;
    private String createAt;
    private String updateAt;
    private String productThumbnailImage;
    private ProductImages productImages;

    @Builder
    public FindProductResponseView(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.explanation = product.getExplanation();
        this.price = product.getPrice();
        this.tax = product.getTax();
        this.isUsed = product.getIsUsed();
        this.isSold = product.getIsSold();
        this.categoryId = product.getCategoryId();
        this.createAt = product.getFormattedCreateDate();
        this.updateAt = product.getFormattedModifyDate();
        this.productThumbnailImage = product.getProductThumbnailImage();
        this.productImages = product.getProductImages();
    }

    public static FindProductResponseView toDto(Product product) {
        return FindProductResponseView.builder()
                .product(product)
                .build();
    }
}
