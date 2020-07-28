package reuse.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.stream.Collectors;

@Embeddable
@NoArgsConstructor
public class ProductImages {
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private List<Image> images;


    @Builder
    public ProductImages(List<Image> images) {
        this.images = images;
    }

    public static ProductImages toEntity(List<String> imageUrls, Product savedProduct) {
        List<Image> images = imageUrls.stream()
                .map(url -> new Image(savedProduct, url))
                .collect(Collectors.toList());

        return new ProductImages(images);
    }

    public int getSize() {
        return this.images.size();
    }

    public String getIndexImage(int index) {
        return this.images.get(index).getUrl();
    }
}
