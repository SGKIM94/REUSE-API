package reuse.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static reuse.fixture.ProductFixture.TEST_PRODUCT;
import static reuse.fixture.ProductImagesFixture.TEST_IMAGE_URLS;

public class ImageTest {
    @DisplayName("이미지 URL 과 Product 로 Image Entity 를 만들 수 있는지")
    @Test
    public void convertUrlsToImages() {
        //when
        List<Image> images = Image.convertUrlsToImages(TEST_PRODUCT, TEST_IMAGE_URLS);

        assertThat(images.size()).isEqualTo(5);
    }
}
