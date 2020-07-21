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
        List<MultipartFile> images = product.getImages();
        return ProductImagesView.builder()
                .firstImage(images.get(0))
                .secondImage(images.get(1))
                .thirdImage(images.get(2))
                .fourthImage(images.get(3))
                .fifthImage(images.get(4))
                .sixthImage(images.get(5))
                .build();
    }

    public List<MultipartFile> convertToList() {
        // null 체크가 필요할지?
        return Arrays.asList(firstImage, secondImage, thirdImage, fourthImage, fifthImage, sixthImage);
    }
}
