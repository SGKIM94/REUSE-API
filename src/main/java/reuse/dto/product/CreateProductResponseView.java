package reuse.dto.product;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reuse.domain.Product;

@Getter
@Setter
@NoArgsConstructor
public class CreateProductResponseView {
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
    private String productImage;

    @Builder
    public CreateProductResponseView(Product product, String productImage) {
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
        this.productImage = productImage;
    }

    public static CreateProductResponseView toDto(Product savedProduct, String productImage) {
        return CreateProductResponseView.builder()
                .product(savedProduct)
                .productImage(productImage)
                .build();
    }
}
