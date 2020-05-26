package reuse.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Getter
public class ProductImages extends AbstractEntity {
    private String firstImage;

    private String secondImage;

    private String thirdImage;

    private String fourthImage;

    private Boolean fifthImage;

    private Boolean sixImage;


    @Builder
    public ProductImages(long id, String firstImage, String secondImage, String thirdImage, String fourthImage,
                         Boolean fifthImage, Boolean sixImage) {
        super(id);
        this.firstImage = firstImage;
        this.secondImage = secondImage;
        this.thirdImage = thirdImage;
        this.fourthImage = fourthImage;
        this.fifthImage = fifthImage;
        this.sixImage = sixImage;
    }
}
