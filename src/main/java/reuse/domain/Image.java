package reuse.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@Getter
public class Image extends AbstractEntity {
    @ManyToOne
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
}
