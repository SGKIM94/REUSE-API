package reuse.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import reuse.domain.Product;
import reuse.domain.ProductImages;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CreateProductRequestView {
    private Long id;
    private String name;
    private String explanation;
    private String price;
    private String tax = "0";
    private Boolean isUsed;
    private Boolean isSold;
    private String categoryId;
    private String quality;
    private String createAt;
    private String updateAt;

    @JsonIgnore
    private List<MultipartFile> images;

    @Builder
    public CreateProductRequestView(Long id, String name, String explanation, String price, String tax, Boolean isUsed,
                                    Boolean isSold, String categoryId, String createAt, String updateAt, String quality,
                                    List<MultipartFile> images) {
        this.id = id;
        this.name = name;
        this.explanation = explanation;
        this.price = price;
        this.tax = tax;
        this.isUsed = isUsed;
        this.isSold = isSold;
        this.categoryId = categoryId;
        this.quality = quality;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.images = images;
    }

    public Product toEntity(CreateProductRequestView product, ProductImages productImages) {
        return Product.builder()
                .explanation(product.getExplanation())
                .isSold(product.getIsSold())
                .isUsed(product.getIsUsed())
                .name(product.getName())
                .price(product.getPrice())
                .tax(product.getTax())
                .quality(product.getQuality())
                .productImages(productImages)
                .build();
    }
}
