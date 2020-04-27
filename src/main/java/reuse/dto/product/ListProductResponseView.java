package reuse.dto.product;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    public static ListProductResponseView toDto(List<Product> products) {
        return new ListProductResponseView(
                products.stream()
                .map(FindProductResponseView::new)
                .collect(Collectors.toList()));
    }

    public int getSize() {
        return this.products.size();
    }
}
