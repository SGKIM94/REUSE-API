package reuse.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Embeddable
@NoArgsConstructor
@Slf4j
public class ProductImages {
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();

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

    @JsonIgnore
    public int getSize() {
        return this.images.size();
    }

    @JsonIgnore
    public String getIndexImage(int index) {
        if (this.images.get(index) == null) {
            log.error("해당 Images : " + images.toString());
            throw new IllegalArgumentException("해당 Index 에 Product Image 가 존재하지 않습니다. : " + index);
        }

        return this.images.get(index).getUrl();
    }
}
