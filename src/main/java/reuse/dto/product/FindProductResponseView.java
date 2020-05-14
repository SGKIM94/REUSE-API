package reuse.dto.product;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.io.Resource;
import reuse.domain.Product;

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
    private List<String> productImages;

    @Builder
    public FindProductResponseView(Product product, List<String> productImages) {
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

    public static FindProductResponseView toDto(Product product) {
        return FindProductResponseView.builder()
                .product(product)
                .build();
    }
}
