package reuse.fixture;

import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

public class ProductImagesFixture extends CommonFixture {
    public static final String TEST_IMAGE_FILE_NAME1 = "foo1.txt";
    public static final String TEST_IMAGE_FILE_NAME2 = "foo2.txt";
    public static final String TEST_IMAGE_FILE_NAME3 = "foo3.txt";
    public static final String TEST_IMAGE_FILE_NAME4 = "foo4.txt";
    public static final String TEST_IMAGE_FILE_NAME5 = "foo5.txt";
    public static final String TEST_IMAGE_FILE_NAME6 = "foo6.txt";

    public static final MultipartFile TEST_IMAGE1
            = new MockMultipartFile("foo1", TEST_IMAGE_FILE_NAME1, MediaType.TEXT_PLAIN_VALUE, "test file".getBytes());
    public static final MultipartFile TEST_IMAGE2
            = new MockMultipartFile("foo2", TEST_IMAGE_FILE_NAME2, MediaType.TEXT_PLAIN_VALUE, "test file".getBytes());
    public static final MultipartFile TEST_IMAGE3
            = new MockMultipartFile("foo3", TEST_IMAGE_FILE_NAME3, MediaType.TEXT_PLAIN_VALUE, "test file".getBytes());
    public static final MultipartFile TEST_IMAGE4
            = new MockMultipartFile("foo4", TEST_IMAGE_FILE_NAME4, MediaType.TEXT_PLAIN_VALUE, "test file".getBytes());
    public static final MultipartFile TEST_IMAGE5
            = new MockMultipartFile("foo5", TEST_IMAGE_FILE_NAME5, MediaType.TEXT_PLAIN_VALUE, "test file".getBytes());
    public static final MultipartFile TEST_IMAGE6
            = new MockMultipartFile("foo6", TEST_IMAGE_FILE_NAME6, MediaType.TEXT_PLAIN_VALUE, "test file".getBytes());
}
