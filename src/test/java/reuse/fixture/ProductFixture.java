package reuse.fixture;

import reuse.domain.Product;
import reuse.dto.product.CreateProductRequestView;

import java.util.Arrays;
import java.util.List;

public class ProductFixture extends CommonFixture {
    public static final String TEST_PRODUCT_NAME = "핸드폰";
    public static final String TEST_PRODUCT_EXPLANATION = "테스트 상품";
    public static final String TEST_PRICE = "20000";
    public static final String TEST_TAX = "200";

    public static final Product TEST_PRODUCT = Product.builder()
            .id(DEFAULT_ID).name(TEST_PRODUCT_NAME).explanation(TEST_PRODUCT_EXPLANATION)
            .price(TEST_PRICE).tax(TEST_TAX).isSold(false).isUsed(true).build();

    public static final Product SECOND_TEST_PRODUCT = Product.builder()
            .id(SECOND_ID).name(TEST_PRODUCT_NAME).explanation(TEST_PRODUCT_EXPLANATION)
            .price(TEST_PRICE).tax(TEST_TAX).isSold(false).isUsed(true).build();

    public static final CreateProductRequestView CREATE_PRODUCT_REQUEST_DTO = CreateProductRequestView.builder()
            .product(TEST_PRODUCT).build();

    public static final CreateProductRequestView SECOND_CREATE_PRODUCT_REQUEST_DTO = CreateProductRequestView.builder()
            .product(SECOND_TEST_PRODUCT).build();

    public static final List<Product> LIST_PRODUCT_RESPONSE_VIEW
            = Arrays.asList(TEST_PRODUCT, SECOND_TEST_PRODUCT);
}
