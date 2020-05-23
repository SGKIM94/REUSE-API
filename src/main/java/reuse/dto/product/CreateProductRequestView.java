package reuse.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import reuse.domain.Product;

@Getter
@Setter
@NoArgsConstructor
public class CreateProductRequestView {
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
    @JsonIgnore
    private ProductImagesView productImages;
    @JsonIgnore
    private MultipartFile productThumbnailImage;

    @Builder
    public CreateProductRequestView(Product product, ProductImagesView productImages, MultipartFile productThumbnailImage) {
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
        this.productImages = productImages;
        this.productThumbnailImage = productThumbnailImage;
    }

    public Product toEntity(CreateProductRequestView product) {
        return Product.builder()
                .id(product.getId())
                .explanation(product.getExplanation())
                .isSold(product.getIsSold())
                .isUsed(product.getIsUsed())
                .name(product.getName())
                .price(product.getPrice())
                .tax(product.getTax())
                .categoryId(product.getCategoryId())
                .build();
    }
}
