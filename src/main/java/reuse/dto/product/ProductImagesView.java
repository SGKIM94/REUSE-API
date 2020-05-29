package reuse.dto.product;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductImagesView {
    private MultipartFile firstImage;
    private MultipartFile secondImage;
    private MultipartFile thirdImage;
    private MultipartFile fourthImage;
    private MultipartFile fifthImage;
    private MultipartFile sixthImage;

    @Builder
    public ProductImagesView(MultipartFile firstImage, MultipartFile secondImage, MultipartFile thirdImage,
                             MultipartFile fourthImage, MultipartFile fifthImage, MultipartFile sixthImage) {
        this.firstImage = firstImage;
        this.secondImage = secondImage;
        this.thirdImage = thirdImage;
        this.fourthImage = fourthImage;
        this.fifthImage = fifthImage;
        this.sixthImage = sixthImage;
    }

    public static ProductImagesView toDtoByCreate(CreateProductRequestView product) {
        return ProductImagesView.builder()
                .firstImage(product.getFirstImage())
                .secondImage(product.getSecondImage())
                .thirdImage(product.getThirdImage())
                .fourthImage(product.getFourthImage())
                .fifthImage(product.getFifthImage())
                .sixthImage(product.getSixthImage())
                .build();
    }

    public List<MultipartFile> convertToList() {
        // null 체크가 필요할지?
        return Arrays.asList(firstImage, secondImage, thirdImage, fourthImage, fifthImage, sixthImage);
    }
}
