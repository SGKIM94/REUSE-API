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
import reuse.repository.CategoryRepository;
import reuse.repository.ImageRepository;
import reuse.repository.ProductRepository;
import reuse.storage.S3Uploader;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static reuse.fixture.ProductFixture.DEFAULT_ID;
import static reuse.fixture.ProductFixture.*;
import static reuse.fixture.ProductImagesFixture.TEST_IMAGES;
import static reuse.fixture.ProductImagesFixture.*;
import static reuse.service.ProductService.S3_PRODUCT_IMAGES_DIRECTORY_NAME;

@SpringBootTest
public class ProductServiceTest {
    public static final String S3_TEST_PRODUCT_IMAGES_DIRECTORY_NAME = "tests/";

    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private ImageRepository imageRepository;

    @SpyBean
    private S3Uploader s3Uploader;

    @BeforeEach
    void setUp() {
        this.productService = new ProductService(productRepository, imageRepository, s3Uploader);
    }

    @DisplayName("품목이 생성되는지")
    @Test
    public void create() {
        when(productRepository.save(any())).thenReturn(TEST_PRODUCT);
        when(imageRepository.saveAll(any())).thenReturn(TEST_IMAGES);

        CreateProductResponseView product = productService.create(CREATE_PRODUCT_REQUEST_DTO);
        assertThat(product.getId()).isNotNull();

        verify(productRepository).save(any());
        verify(imageRepository).saveAll(any());
    }

    @DisplayName("품목들이 조회되는지")
    @Test
    public void list() {
        when(productRepository.findAll()).thenReturn(LIST_PRODUCT_RESPONSE_VIEW);

        ListProductResponseView products = productService.list();

        List<FindProductResponseView> findProductResponseViews = products.getProducts();
        FindProductResponseView findProductResponseView = findProductResponseViews.get(0);
        List<String> productImages = findProductResponseView.getProductImages();

        assertThat(products.getSize()).isGreaterThan(1);
        assertThat(productImages.size()).isEqualTo(6);
        verify(productRepository).findAll();
    }

    @DisplayName("품목 상세 내역이 조회되는지")
    @Test
    public void find() {
        when(productRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(TEST_PRODUCT));

        Product product = productService.findById(DEFAULT_ID);
        ProductImages productImages = product.getProductImages();

        assertThat(product.getName()).isEqualTo(TEST_PRODUCT_NAME);
        assertThat(productImages.getIndexImage(0)).isEqualTo(FIRST_IMAGE_URL);

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

    @DisplayName("품목의 이미지들을 저장하는지")
    @Test
    public void storeProductImagesTest() {
        when(imageRepository.save(any())).thenReturn(TEST_FIRST_IMAGE);
        when(imageRepository.save(any())).thenReturn(TEST_SECOND_IMAGE);

        ProductImages productImages = productService.storeProductImages
                (CREATE_PRODUCT_REQUEST_DTO, S3_TEST_PRODUCT_IMAGES_DIRECTORY_NAME, TEST_PRODUCT);

        //then
        assertThat(productImages.getSize()).isEqualTo(2);

        assertThat(productImages.getIndexImage(0)).isNotBlank();
        assertThat(productImages.getIndexImage(1)).isNotBlank();
    }

    @DisplayName("ProductResponseView 를 이미지 url 들과 같이 생성하는지")
    @Test
    public void toFindProductResponseViewWithFiles() {
        FindProductResponseView response = ListProductResponseView.toFindProductResponseViewWithFiles(TEST_PRODUCT);
        List<String> productImages = response.getProductImages();

        //then
        assertThat(response.getName()).isEqualTo(TEST_PRODUCT_NAME);
        assertThat(productImages.get(0)).isEqualTo(FIRST_IMAGE_URL);
    }

    @DisplayName("product image 를 ProductImagesView 로 부터 가져올 수 있는지")
    @Test
    public void storeProductImageByProductImagesView() {
        List<String> imageUrls
                = productService.storeProductImageByProductImagesView(TEST_MULTIPART_FILES, S3_PRODUCT_IMAGES_DIRECTORY_NAME);

        //then
        assertThat(imageUrls.size()).isEqualTo(4);
    }
}
