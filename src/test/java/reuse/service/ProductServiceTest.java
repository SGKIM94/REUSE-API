package reuse.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reuse.domain.Product;
import reuse.domain.ProductImages;
import reuse.dto.product.CreateProductResponseView;
import reuse.dto.product.FindProductResponseView;
import reuse.dto.product.ListProductResponseView;
import reuse.repository.ProductRepository;
import reuse.repository.ProductImagesRepository;
import reuse.security.TokenAuthenticationService;
import reuse.storage.S3Uploader;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static reuse.fixture.ProductFixture.*;
import static reuse.fixture.ProductImagesFixture.*;

@SpringBootTest
public class ProductServiceTest {
    public static final String S3_TEST_PRODUCT_IMAGES_DIRECTORY_NAME = "tests/";

    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private ProductImagesRepository productImagesRepository;

    @SpyBean
    private S3Uploader s3Uploader;

    @MockBean
    private TokenAuthenticationService tokenAuthenticationService;

    @BeforeEach
    void setUp() {
        this.tokenAuthenticationService = new TokenAuthenticationService();
        this.productService = new ProductService(productRepository, productImagesRepository, s3Uploader);
    }

    @DisplayName("품목이 생성되는지")
    @Test
    public void create() {
        when(productRepository.save(any())).thenReturn(TEST_PRODUCT);
        when(productImagesRepository.save(any())).thenReturn(TEST_PRODUCT_IMAGES);

        CreateProductResponseView product = productService.create(CREATE_PRODUCT_REQUEST_DTO);
        assertThat(product.getId()).isNotNull();

        verify(productRepository).save(any());
        verify(productImagesRepository).save(any());
    }

    @DisplayName("품목들이 조회되는지")
    @Test
    public void list() {
        when(productRepository.findAll()).thenReturn(LIST_PRODUCT_RESPONSE_VIEW);

        ListProductResponseView products = productService.list();

        List<FindProductResponseView> findProductResponseViews = products.getProducts();
        FindProductResponseView findProductResponseView = findProductResponseViews.get(0);
        ProductImages productImages = findProductResponseView.getProductImages();

        assertThat(products.getSize()).isGreaterThan(1);
        assertThat(productImages.getFirstImage()).isNotBlank();
        verify(productRepository).findAll();
    }

    @DisplayName("품목 상세 내역이 조회되는지")
    @Test
    public void find() {
        when(productRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(TEST_PRODUCT));

        Product product = productService.findById(DEFAULT_ID);
        ProductImages productImages = product.getProductImages();
        String thumbnailImage = product.getThumbnailImage();

        assertThat(product.getName()).isEqualTo(TEST_PRODUCT_NAME);
        assertThat(productImages.getFirstImage()).isEqualTo(FIRST_IMAGE_URL);
        assertThat(thumbnailImage).isNotBlank();

        verify(productRepository).findById(any());
    }

    @DisplayName("품목 상세 내역을 이미지들과 조회되는지")
    @Test
    public void findWithImages() {
        when(productRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(TEST_PRODUCT));

        FindProductResponseView product = productService.findByIdWithImages(DEFAULT_ID);
        ProductImages productImages = product.getProductImages();
        String productImage = productImages.getFirstImage();

        assertThat(product.getName()).isEqualTo(TEST_PRODUCT_NAME);
        assertThat(productImage).isNotBlank();

        verify(productRepository).findById(any());
    }

    @DisplayName("품목 이미지를 단일로 저장 가능한지")
    @Test
    public void storeProductImage() {
        String imageUrl = productService.storeProductImage
                (TEST_IMAGE1, S3_TEST_PRODUCT_IMAGES_DIRECTORY_NAME + 3L);

        //then
        assertThat(imageUrl).isNotBlank();
    }

    @DisplayName("품목의 섬네일 이미지를 저장하는지")
    @Test
    public void storeThumbnailImage() {
        String imageUrl = productService.storeThumbnailImage
                (CREATE_PRODUCT_REQUEST_DTO, S3_TEST_PRODUCT_IMAGES_DIRECTORY_NAME);

        //then
        assertThat(imageUrl).isNotBlank();
    }

    @DisplayName("품목의 이미지들을 저장하는지")
    @Test
    public void storeProductImagesTest() {
        List<String> imageUrl = productService.storeProductImages
                (CREATE_PRODUCT_REQUEST_DTO, S3_TEST_PRODUCT_IMAGES_DIRECTORY_NAME);

        //then
        assertThat(imageUrl.size()).isGreaterThan(4);
    }

    @DisplayName("ProductResponseView 를 이미지 url 들과 같이 생성하는지")
    @Test
    public void toFindProductResponseViewWithFiles() {
        FindProductResponseView response = ListProductResponseView.toFindProductResponseViewWithFiles(TEST_PRODUCT);
        ProductImages productImages = response.getProductImages();

        //then
        assertThat(response.getName()).isEqualTo(TEST_PRODUCT_NAME);
        assertThat(response.getThumbnailImage()).isNotBlank();
        assertThat(productImages.getFirstImage()).isEqualTo(FIRST_IMAGE_URL);
    }
}
