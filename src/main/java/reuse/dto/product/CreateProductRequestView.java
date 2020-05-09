package reuse.dto.product;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import reuse.domain.Product;

import java.util.List;

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
    private List<MultipartFile> productImages;

    @Builder
    public CreateProductRequestView(Product product, List<MultipartFile> productImages) {
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
