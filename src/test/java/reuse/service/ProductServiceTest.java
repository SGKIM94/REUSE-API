package reuse.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reuse.domain.Product;
import reuse.dto.product.CreateProductResponseView;
import reuse.dto.product.FindProductResponseView;
import reuse.dto.product.ListProductResponseView;
import reuse.repository.ProductRepository;
import reuse.security.TokenAuthenticationService;
import reuse.storage.S3Uploader;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static reuse.fixture.ProductFixture.*;

@SpringBootTest
public class ProductServiceTest {
    public static final String S3_TEST_PRODUCT_IMAGES_DIRECTORY_NAME = "tests/";
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @SpyBean
    private S3Uploader s3Uploader;

    @MockBean
    private TokenAuthenticationService tokenAuthenticationService;

    @BeforeEach
    void setUp() {
        this.tokenAuthenticationService = new TokenAuthenticationService();
        this.productService = new ProductService(productRepository, s3Uploader);
    }

    @DisplayName("품목이 생성되는지")
    @Test
    public void create() {
        when(productRepository.save(any())).thenReturn(TEST_PRODUCT);

        CreateProductResponseView product = productService.create(CREATE_PRODUCT_REQUEST_DTO);

        assertThat(product.getName()).isEqualTo(TEST_PRODUCT_NAME);
        verify(productRepository).save(any());
    }

    @DisplayName("품목들이 조회되는지")
    @Test
    public void list() {
        when(productRepository.findAll()).thenReturn(LIST_PRODUCT_RESPONSE_VIEW);

        ListProductResponseView products = productService.list();

        List<FindProductResponseView> findProductResponseViews = products.getProducts();
        FindProductResponseView findProductResponseView = findProductResponseViews.get(0);
        List<String> productImages = findProductResponseView.getProductImages();
        String firstResource = productImages.get(0);

        assertThat(products.getSize()).isGreaterThan(1);
        assertThat(firstResource).isNotBlank();
        verify(productRepository).findAll();
    }

    @DisplayName("품목 상세 내역이 조회되는지")
    @Test
    public void find() {
        when(productRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(TEST_PRODUCT));

        Product product = productService.findById(DEFAULT_ID);

        assertThat(product.getName()).isEqualTo(TEST_PRODUCT_NAME);

        verify(productRepository).findById(any());
    }


    @DisplayName("품목 상세 내역을 이미지들과 조회되는지")
    @Test
    public void findWithImages() {
        when(productRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(TEST_PRODUCT));

        FindProductResponseView product = productService.findByIdWithImages(DEFAULT_ID);
        List<String> productImages = product.getProductImages();
        String productImage = productImages.get(0);

        assertThat(product.getName()).isEqualTo(TEST_PRODUCT_NAME);
        assertThat(product.getProductImages().size()).isGreaterThan(0);
        assertThat(productImage).isNotBlank();

        verify(productRepository).findById(any());
    }

    @DisplayName("품목 이미지들이 저장되는지")
    @Test
    public void storeProductImagesTest() {
        String imageUrl = productService.storeProductImages(CREATE_PRODUCT_REQUEST_DTO, S3_TEST_PRODUCT_IMAGES_DIRECTORY_NAME);

        //then
        assertThat(imageUrl).isNotBlank();
    }
}
