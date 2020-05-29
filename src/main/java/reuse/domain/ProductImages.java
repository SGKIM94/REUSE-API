package reuse.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class ProductImages extends AbstractEntity {
    private String firstImage;

    private String secondImage;

    private String thirdImage;

    private String fourthImage;

    private String fifthImage;

    private String sixthImage;


    @Builder
    public ProductImages(String firstImage, String secondImage, String thirdImage, String fourthImage,
                         String fifthImage, String sixthImage) {
        this.firstImage = firstImage;
        this.secondImage = secondImage;
        this.thirdImage = thirdImage;
        this.fourthImage = fourthImage;
        this.fifthImage = fifthImage;
        this.sixthImage = sixthImage;
    }

    public static ProductImages toEntity(List<String> imageUrls) {
        return ProductImages.builder()
                .firstImage(imageUrls.get(0))
                .secondImage(imageUrls.get(1))
                .thirdImage(imageUrls.get(2))
                .fourthImage(imageUrls.get(3))
                .fifthImage(imageUrls.get(4))
                .sixthImage(imageUrls.get(5))
                .build();
    }
}
