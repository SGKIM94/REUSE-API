package reuse.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static reuse.fixture.ProductFixture.TEST_PRODUCT;
import static reuse.fixture.ProductImagesFixture.TEST_IMAGE_URLS;
import static reuse.fixture.ProductImagesFixture.TEST_PRODUCT_IMAGES;

public class ProductImagesTest {
    @DisplayName("이미지에 대한 URL 들과 품목으로 ProductImages 를 리턴하는지")
    @Test
    public void toEntity() {
        ProductImages productImages = ProductImages.toEntity(TEST_IMAGE_URLS, TEST_PRODUCT);

        assertThat(productImages.getSize()).isEqualTo(6);
    }

    @DisplayName("product image ")
    @Test
    public void getIndexImage() {
        String message = assertThrows(IllegalArgumentException.class, () -> {
            TEST_PRODUCT_IMAGES.getIndexImage(7);
        }).getMessage();

        assertThat(message).isEqualTo("해당 Index 에 Product Image 가 존재하지 않습니다. : 7");
    }
}
