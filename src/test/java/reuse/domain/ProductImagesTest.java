package reuse.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static reuse.fixture.ProductFixture.TEST_PRODUCT;
import static reuse.fixture.ProductImagesFixture.*;

public class ProductImagesTest {
    @DisplayName("이미지에 대한 URL 들과 품목으로 ProductImages 를 리턴하는지")
    @Test
    public void toEntity() {
        ProductImages productImages = ProductImages.toEntity(TEST_IMAGE_URLS, TEST_PRODUCT);

        assertThat(productImages.getSize()).isEqualTo(5);
    }

    @DisplayName("product image 의 초과된 index 를 조회했을 때의 예외 처리")
    @Test
    public void getIndexImage() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            TEST_PRODUCT_IMAGES.getIndexImage(7);
        });
    }

    @DisplayName("게시판 리스트에 노출시키기 위한 메인 이미지를 가져오는지")
    @Test
    public void retrieveMainUrl() {
        String mainImage = TEST_PRODUCT_IMAGES.retrieveMainImage();

        assertThat(mainImage).isEqualTo(FIRST_IMAGE_URL);
    }

    @DisplayName("품목 상세에서 이미지들이 조회되는지")
    @Test
    public void retrieveImages() {
        List<String> images = TEST_PRODUCT_IMAGES.retrieveImages();

        assertThat(images.size()).isEqualTo(6);
    }
}
