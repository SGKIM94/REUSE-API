package reuse.fixture;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import reuse.domain.Product;
import reuse.dto.product.CreateProductRequestView;
import reuse.dto.product.ProductImagesView;

import java.util.Arrays;
import java.util.List;

import static reuse.fixture.ProductImagesFixture.*;

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

    public static final List<MultipartFile> TEST_IMAGES = Arrays.asList(TEST_IMAGE1, TEST_IMAGE2);

    public static final ProductImagesView TEST_PRODUCT_IMAGES_VIEW = ProductImagesView.builder()
            .productImage1(TEST_IMAGE1).productImage2(TEST_IMAGE2).productImage3(TEST_IMAGE3).productImage4(TEST_IMAGE4)
            .productImage5(TEST_IMAGE5).productImage6(TEST_IMAGE6).build();

    public static final CreateProductRequestView CREATE_PRODUCT_REQUEST_DTO = CreateProductRequestView.builder()
            .product(TEST_PRODUCT).productThumbnailImage(TEST_IMAGE1).productImages(TEST_PRODUCT_IMAGES_VIEW).build();

    public static final CreateProductRequestView SECOND_CREATE_PRODUCT_REQUEST_DTO = CreateProductRequestView.builder()
            .product(SECOND_TEST_PRODUCT).build();

    public static final List<Product> LIST_PRODUCT_RESPONSE_VIEW
            = Arrays.asList(TEST_PRODUCT, SECOND_TEST_PRODUCT);

    public static MultiValueMap<String, Object> getCreateProductMap() {
        MultiValueMap<String, Object> product = new LinkedMultiValueMap<>();
        product.add("id", DEFAULT_ID);
        product.add("name", TEST_PRODUCT_NAME);
        product.add("explanation", TEST_PRODUCT_EXPLANATION);
        product.add("price", TEST_PRICE);
        product.add("tax", TEST_TAX);
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
