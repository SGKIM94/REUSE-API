package reuse.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import reuse.domain.Product;
import reuse.domain.ProductImages;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CreateProductRequestView {
    private Long id;
    private String name;
    private String explanation;
    private String price;
    private String tax = "0";
    private Boolean isUsed;
    private Boolean isSold;
    private String categoryId;
    private String createAt;
    private String updateAt;
    @JsonIgnore
    private MultipartFile firstImage;
    @JsonIgnore
    private MultipartFile secondImage;
    @JsonIgnore
    private MultipartFile thirdImage;
    @JsonIgnore
    private MultipartFile fourthImage;
    @JsonIgnore
    private MultipartFile fifthImage;
    @JsonIgnore
    private MultipartFile sixthImage;
    @JsonIgnore
    private MultipartFile thumbnailImage;

    @Builder
    public CreateProductRequestView(Long id, String name, String explanation, String price, String tax, Boolean isUsed,
                                    Boolean isSold, String categoryId, String createAt, String updateAt, MultipartFile firstImage,
                                    MultipartFile secondImage, MultipartFile thirdImage, MultipartFile fourthImage, MultipartFile fifthImage, MultipartFile sixthImage, MultipartFile thumbnailImage) {
        this.id = id;
        this.name = name;
        this.explanation = explanation;
        this.price = price;
        this.tax = tax;
        this.isUsed = isUsed;
        this.isSold = isSold;
        this.categoryId = categoryId;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.firstImage = firstImage;
        this.secondImage = secondImage;
        this.thirdImage = thirdImage;
        this.fourthImage = fourthImage;
        this.fifthImage = fifthImage;
        this.sixthImage = sixthImage;
        this.thumbnailImage = thumbnailImage;
    }

    public Product toEntity(CreateProductRequestView product, String thumbnailImage, List<String> imageUrls) {
        ProductImages productImages = ProductImages.toEntity(imageUrls);

        return Product.builder()
                .explanation(product.getExplanation())
                .isSold(product.getIsSold())
                .isUsed(product.getIsUsed())
                .name(product.getName())
                .price(product.getPrice())
                .tax(product.getTax())
                .thumbnailImage(thumbnailImage)
                .productImages(productImages)
                .categoryId(product.getCategoryId())
                .build();
    }
}
