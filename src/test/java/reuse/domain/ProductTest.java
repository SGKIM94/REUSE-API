package reuse.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static reuse.fixture.ProductImagesFixture.TEST_PRODUCT_IMAGES;

public class ProductTest {
    @DisplayName("ProductImages 를 List<String> 형태로 변경하는지")
    @Test
    public void convertImagesToView() {
        List<String> viewImages = TEST_PRODUCT_IMAGES.convertImagesToView();

        assertThat(viewImages.size()).isEqualTo(5);
    }
}


