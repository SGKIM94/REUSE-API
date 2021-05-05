package reuse.dto.product;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import reuse.domain.Product;

import java.util.List;

@Getter
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
    private String quality;
    private String createAt;
    private String updateAt;
    private String mainImage;
    private List<String> productImages;

    @Builder
    public FindProductResponseView(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.explanation = product.getExplanation();
        this.price = product.getPrice();
        this.tax = product.getTax();
        this.isUsed = product.getIsUsed();
        this.isSold = product.getIsSold();
        this.quality = product.getQuality();
//        this.categoryId = product.getCategoryId();
        this.mainImage = product.retrieveMainImage();
        this.createAt = product.getFormattedCreateDate();
        this.updateAt = product.getFormattedModifyDate();
        this.productImages = product.retrieveImages();
    }

    public static FindProductResponseView toDto(Product product) {
        if (product == null) {
            return new FindProductResponseView();
        }

        return FindProductResponseView.builder()
                .product(product)
                .build();
    }
}
