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
    private MultipartFile sixImage;

    @Builder
    public ProductImagesView(MultipartFile firstImage, MultipartFile secondImage, MultipartFile thirdImage,
                             MultipartFile fourthImage, MultipartFile fifthImage, MultipartFile sixImage) {
        this.firstImage = firstImage;
        this.secondImage = secondImage;
        this.thirdImage = thirdImage;
        this.fourthImage = fourthImage;
        this.fifthImage = fifthImage;
        this.sixImage = sixImage;
    }

    public List<MultipartFile> convertToList() {
        // null 체크가 필요할지?
        return Arrays.asList
                (firstImage, secondImage, thirdImage, fourthImage, fifthImage, sixImage);
    }
}
