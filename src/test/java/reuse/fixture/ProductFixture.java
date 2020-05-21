package reuse.fixture;

import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import reuse.domain.Product;
import reuse.dto.product.CreateProductRequestView;

import java.util.Arrays;
import java.util.List;

public class ProductFixture extends CommonFixture {
    public static final String TEST_PRODUCT_NAME = "핸드폰";
    public static final String TEST_PRODUCT_EXPLANATION = "테스트 상품";
    public static final String TEST_PRICE = "20000";
    public static final String TEST_TAX = "200";
    public static final String TEST_IMAGE_FILE_NAME1 = "foo1.txt";
    public static final String TEST_IMAGE_FILE_NAME2 = "foo2.txt";

    public static final Product TEST_PRODUCT = Product.builder()
            .id(DEFAULT_ID).name(TEST_PRODUCT_NAME).explanation(TEST_PRODUCT_EXPLANATION)
            .price(TEST_PRICE).tax(TEST_TAX).isSold(false).isUsed(true).build();

    public static final Product SECOND_TEST_PRODUCT = Product.builder()
            .id(SECOND_ID).name(TEST_PRODUCT_NAME).explanation(TEST_PRODUCT_EXPLANATION)
            .price(TEST_PRICE).tax(TEST_TAX).isSold(false).isUsed(true).build();

    public static final MultipartFile TEST_IMAGE1
            = new MockMultipartFile("foo1", TEST_IMAGE_FILE_NAME1, MediaType.TEXT_PLAIN_VALUE, "test file".getBytes());
    public static final MultipartFile TEST_IMAGE2
            = new MockMultipartFile("foo2", TEST_IMAGE_FILE_NAME2, MediaType.TEXT_PLAIN_VALUE, "test file".getBytes());

    public static final List<MultipartFile> TEST_IMAGES = Arrays.asList(TEST_IMAGE1, TEST_IMAGE2);

    public static final CreateProductRequestView CREATE_PRODUCT_REQUEST_DTO = CreateProductRequestView.builder()
            .product(TEST_PRODUCT).productImages(TEST_IMAGES).productImage(TEST_IMAGE1).build();

    public static final CreateProductRequestView SECOND_CREATE_PRODUCT_REQUEST_DTO = CreateProductRequestView.builder()
            .product(SECOND_TEST_PRODUCT).build();

    public static final List<Product> LIST_PRODUCT_RESPONSE_VIEW
            = Arrays.asList(TEST_PRODUCT, SECOND_TEST_PRODUCT);
}
