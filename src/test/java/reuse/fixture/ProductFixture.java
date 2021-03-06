package reuse.fixture;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import reuse.domain.Product;
import reuse.dto.product.CreateProductRequestView;

import java.util.Arrays;
import java.util.List;

import static reuse.fixture.ProductImagesFixture.*;

public class ProductFixture extends CommonFixture {
    public static final Long TEST_PRODUCT_ID = 9999999999L;
    public static final String TEST_PRODUCT_NAME = "테스트 품목";
    public static final String TEST_SECOND_PRODUCT_NAME = "테스트 품목2";
    public static final String TEST_PRODUCT_EXPLANATION = "테스트 상품";
    public static final String TEST_PRICE = "20000";
    public static final String TEST_TAX = "200";

    public static final Product TEST_PRODUCT = Product.testBuilder()
            .id(TEST_PRODUCT_ID).name(TEST_PRODUCT_NAME).explanation(TEST_PRODUCT_EXPLANATION)
            .price(TEST_PRICE).tax(TEST_TAX).isSold(false).isUsed(true).productImages(TEST_PRODUCT_IMAGES)
            .build();

    public static final Product SECOND_TEST_PRODUCT = Product.builder()
            .name(TEST_PRODUCT_NAME).explanation(TEST_PRODUCT_EXPLANATION)
            .price(TEST_PRICE).tax(TEST_TAX).isSold(false).isUsed(true).productImages(TEST_PRODUCT_IMAGES).build();

    public static final List<MultipartFile> TEST_IMAGES = Arrays.asList(TEST_IMAGE1, TEST_IMAGE2);

    public static final CreateProductRequestView CREATE_PRODUCT_REQUEST_DTO = CreateProductRequestView.builder()
            .images(TEST_IMAGES).build();

    public static final List<Product> LIST_PRODUCT_RESPONSE_VIEW = Arrays.asList(TEST_PRODUCT, SECOND_TEST_PRODUCT);

    public static MultiValueMap<String, Object> getCreateProductMap(Long categoryId) {
        MultiValueMap<String, Object> product = new LinkedMultiValueMap<>();
        product.add("id", DEFAULT_ID);
        product.add("name", TEST_PRODUCT_NAME);
        product.add("explanation", TEST_PRODUCT_EXPLANATION);
        product.add("price", TEST_PRICE);
        product.add("tax", TEST_TAX);
        product.add("categoryId", categoryId);
        product.add("isUsed", true);
        product.add("isSold", true);

        return product;
    }

    public static MultiValueMap<String, Object> getSecondCreateProductMap() {
        MultiValueMap<String, Object> product = new LinkedMultiValueMap<>();
        product.add("id", SECOND_ID);
        product.add("name", TEST_PRODUCT_NAME);
        product.add("explanation", TEST_PRODUCT_EXPLANATION);
        product.add("price", TEST_PRICE);
        product.add("tax", TEST_TAX);
        product.add("isUsed", true);
        product.add("isSold", true);

        return product;
    }


}
