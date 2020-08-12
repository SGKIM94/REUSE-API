package reuse.fixture;

import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import reuse.domain.Image;
import reuse.domain.ProductImages;

import java.util.Arrays;
import java.util.List;

import static reuse.fixture.ProductFixture.TEST_PRODUCT;

public class ProductImagesFixture extends CommonFixture {
    public static final String TEST_IMAGE_FILE_NAME1 = "foo1.txt";
    public static final String TEST_IMAGE_FILE_NAME2 = "foo2.txt";
    public static final String TEST_IMAGE_FILE_NAME3 = "foo3.txt";
    public static final String TEST_IMAGE_FILE_NAME4 = "foo4.txt";
    public static final String TEST_IMAGE_FILE_NAME5 = "foo5.txt";
    public static final String TEST_IMAGE_FILE_NAME6 = "foo6.txt";

    public static final String TEST_FILE_CONTENT = "test file!";

    public static final MultipartFile TEST_IMAGE1
            = new MockMultipartFile("foo1", TEST_IMAGE_FILE_NAME1, MediaType.TEXT_PLAIN_VALUE, TEST_FILE_CONTENT.getBytes());
    public static final MultipartFile TEST_IMAGE2
            = new MockMultipartFile("foo2", TEST_IMAGE_FILE_NAME2, MediaType.TEXT_PLAIN_VALUE, TEST_FILE_CONTENT.getBytes());
    public static final MultipartFile TEST_IMAGE3
            = new MockMultipartFile("foo3", TEST_IMAGE_FILE_NAME3, MediaType.TEXT_PLAIN_VALUE, TEST_FILE_CONTENT.getBytes());
    public static final MultipartFile TEST_IMAGE4
            = new MockMultipartFile("foo4", TEST_IMAGE_FILE_NAME4, MediaType.TEXT_PLAIN_VALUE, TEST_FILE_CONTENT.getBytes());
    public static final MultipartFile TEST_IMAGE5
            = new MockMultipartFile("foo5", TEST_IMAGE_FILE_NAME5, MediaType.TEXT_PLAIN_VALUE, TEST_FILE_CONTENT.getBytes());
    public static final MultipartFile TEST_IMAGE6
            = new MockMultipartFile("foo6", TEST_IMAGE_FILE_NAME6, MediaType.TEXT_PLAIN_VALUE, TEST_FILE_CONTENT.getBytes());

    public static final List<MultipartFile> TEST_MULTIPART_FILES = Arrays.asList(TEST_IMAGE1, TEST_IMAGE2, TEST_IMAGE3, TEST_IMAGE4);

    public static final String FIRST_IMAGE_URL = "https://reuse-s3.s3.ap-northeast-2.amazonaws.com/1/foo1";
    public static final String SECOND_IMAGE_URL = "https://reuse-s3.s3.ap-northeast-2.amazonaws.com/1/foo2";
    public static final String THIRD_IMAGE_URL = "https://reuse-s3.s3.ap-northeast-2.amazonaws.com/1/foo3";
    public static final String FOURTH_IMAGE_URL = "https://reuse-s3.s3.ap-northeast-2.amazonaws.com/1/foo4";
    public static final String FIFTH_IMAGE_URL = "https://reuse-s3.s3.ap-northeast-2.amazonaws.com/1/foo5";
    public static final String SIX_IMAGE_URL = "https://reuse-s3.s3.ap-northeast-2.amazonaws.com/1/foo6";

    public static final Image TEST_FIRST_IMAGE = new Image(TEST_PRODUCT, FIRST_IMAGE_URL);
    public static final Image TEST_SECOND_IMAGE = new Image(TEST_PRODUCT, SECOND_IMAGE_URL);
    public static final Image TEST_THIRD_IMAGE = new Image(TEST_PRODUCT, THIRD_IMAGE_URL);
    public static final Image TEST_FOURTH_IMAGE = new Image(TEST_PRODUCT, FOURTH_IMAGE_URL);
    public static final Image TEST_FIFTH_IMAGE = new Image(TEST_PRODUCT, FIFTH_IMAGE_URL);
    public static final Image TEST_SIX_IMAGE = new Image(TEST_PRODUCT, SIX_IMAGE_URL);


    public static final List<String> TEST_IMAGE_URLS
            = Arrays.asList(FIRST_IMAGE_URL, SECOND_IMAGE_URL, THIRD_IMAGE_URL, FOURTH_IMAGE_URL, FIFTH_IMAGE_URL);

    public static final ProductImages TEST_PRODUCT_IMAGES =
            new ProductImages(Arrays.asList(TEST_FIRST_IMAGE, TEST_SECOND_IMAGE, TEST_THIRD_IMAGE, TEST_FOURTH_IMAGE,
                    TEST_FIFTH_IMAGE, TEST_SIX_IMAGE));

    public static final List<Image> TEST_IMAGES =
            Arrays.asList(TEST_FIRST_IMAGE, TEST_SECOND_IMAGE, TEST_THIRD_IMAGE, TEST_FOURTH_IMAGE,
                    TEST_FIFTH_IMAGE, TEST_SIX_IMAGE);

    public static final ProductImages TEST_SECOND_PRODUCT_IMAGES =
            new ProductImages(Arrays.asList(TEST_FIRST_IMAGE, TEST_SECOND_IMAGE, TEST_THIRD_IMAGE));
}
