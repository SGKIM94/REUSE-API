package reuse.fixture;

import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import reuse.domain.ProductImages;

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

    public static final String FIRST_IMAGE_URL = "https://reuse-s3.s3.ap-northeast-2.amazonaws.com/1/foo1";
    public static final String SECOND_IMAGE_URL = "https://reuse-s3.s3.ap-northeast-2.amazonaws.com/1/foo2";
    public static final String THIRD_IMAGE_URL = "https://reuse-s3.s3.ap-northeast-2.amazonaws.com/1/foo3";
    public static final String FOURTH_IMAGE_URL = "https://reuse-s3.s3.ap-northeast-2.amazonaws.com/1/foo4";
    public static final String FIFTH_IMAGE_URL = "https://reuse-s3.s3.ap-northeast-2.amazonaws.com/1/foo5";
    public static final String SIX_IMAGE_URL = "https://reuse-s3.s3.ap-northeast-2.amazonaws.com/1/foo6";

    public static final ProductImages TEST_PRODUCT_IMAGES = ProductImages.builder()
            .firstImage(FIRST_IMAGE_URL).secondImage(SECOND_IMAGE_URL).thirdImage(THIRD_IMAGE_URL)
            .fourthImage(FOURTH_IMAGE_URL).fifthImage(FIFTH_IMAGE_URL).sixImage(SIX_IMAGE_URL).build();
}
