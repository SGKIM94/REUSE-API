package reuse.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@Getter
public class Image extends AbstractEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;

    private String url;

    @Builder
    public Image(Product product, String url) {
        this.product = product;
        this.url = url;
    }

    public Image(long id, Product product, String url) {
        super(id);
        this.product = product;
        this.url = url;
    }

    public static Image toEntity(String url, Product savedProduct) {
        return Image.builder()
                .product(savedProduct)
                .url(url)
                .build();
    }

    public static List<Image> convertUrlsToImages(Product savedProduct, List<String> imageUrls) {
        return imageUrls.stream()
                .map(url -> Image.toEntity(url, savedProduct))
                .collect(Collectors.toList());
    }
}
