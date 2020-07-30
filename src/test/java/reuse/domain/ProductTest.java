package reuse.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static reuse.fixture.ProductFixture.TEST_PRODUCT;
import static reuse.fixture.ProductImagesFixture.FIRST_IMAGE_URL;

public class ProductTest {
    @DisplayName("게시글의 메인 이미지를 가져오는지")
    @Test
    public void retrieveMainImage() {
        //when
        String mainImage = TEST_PRODUCT.retrieveMainImage();

        assertThat(mainImage).isEqualTo(FIRST_IMAGE_URL);
    }
}
