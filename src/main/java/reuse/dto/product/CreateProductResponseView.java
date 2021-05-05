package reuse.dto.product;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import reuse.domain.Product;

@Getter
@NoArgsConstructor
public class CreateProductResponseView {
    private Long id;

    @Builder
    public CreateProductResponseView(Product product) {
        this.id = product.getId();
    }

    public static CreateProductResponseView toDto(Product savedProduct) {
        return CreateProductResponseView.builder()
                .product(savedProduct)
                .build();
    }
}
