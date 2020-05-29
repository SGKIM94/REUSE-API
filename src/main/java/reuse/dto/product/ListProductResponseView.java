package reuse.dto.product;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.io.Resource;
import reuse.domain.Product;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ListProductResponseView {
    private List<FindProductResponseView> products;

    @Builder
    public ListProductResponseView(List<FindProductResponseView> products) {
        this.products = products;
    }

    public static ListProductResponseView toDto(List<Product> products, List<String> productImages) {
        return new ListProductResponseView(
                products.stream()
                .map(FindProductResponseView::new)
                .collect(Collectors.toList()));
    }

    public static ListProductResponseView toDtoByProducts(List<Product> products) {
        List<FindProductResponseView> findProductViews = products.stream()
                .map(ListProductResponseView::toFindProductResponseViewWithFiles)
                .collect(Collectors.toList());

        return new ListProductResponseView(findProductViews);
    }

    public static FindProductResponseView toFindProductResponseViewWithFiles(Product product) {
        return new FindProductResponseView(product);
    }

    public int getSize() {
        return this.products.size();
    }
}
