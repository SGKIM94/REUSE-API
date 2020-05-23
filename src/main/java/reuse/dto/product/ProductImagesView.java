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
    private MultipartFile productImage1;
    private MultipartFile productImage2;
    private MultipartFile productImage3;
    private MultipartFile productImage4;
    private MultipartFile productImage5;
    private MultipartFile productImage6;

    @Builder
    public ProductImagesView(MultipartFile productImage1, MultipartFile productImage2, MultipartFile productImage3,
                             MultipartFile productImage4, MultipartFile productImage5, MultipartFile productImage6) {
        this.productImage1 = productImage1;
        this.productImage2 = productImage2;
        this.productImage3 = productImage3;
        this.productImage4 = productImage4;
        this.productImage5 = productImage5;
        this.productImage6 = productImage6;
    }

    public List<MultipartFile> convertToList() {
        // null 체크가 필요할지?
        return Arrays.asList
                (productImage1, productImage2, productImage3, productImage4, productImage5, productImage6);
    }
}
