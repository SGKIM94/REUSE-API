package reuse.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reuse.dto.product.CreateProductResponseView;
import reuse.dto.product.FindProductResponseView;
import reuse.dto.product.ListProductResponseView;
import reuse.repository.ProductRepository;
import reuse.security.TokenAuthenticationService;
import reuse.storage.FileSystemStorageService;
import reuse.storage.StorageProperties;

import java.nio.file.Path;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static reuse.fixture.ProductFixture.*;
import static reuse.storage.FileSystemStorageServiceTest.REUSE_LOCATION;

@SpringBootTest
public class ProductServiceTest {
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @SpyBean
    private FileSystemStorageService fileSystemStorageService;

    private StorageProperties storageProperties = new StorageProperties();

    @MockBean
    private TokenAuthenticationService tokenAuthenticationService;

    @BeforeEach
    void setUp() {
        this.tokenAuthenticationService = new TokenAuthenticationService();

        storageProperties.setLocation(REUSE_LOCATION);
        fileSystemStorageService = new FileSystemStorageService(storageProperties);
        fileSystemStorageService.init();

        this.productService = new ProductService(productRepository, fileSystemStorageService);
    }

    @DisplayName("품목이 생성되는지")
    @Test
    public void create() {
        when(productRepository.save(any())).thenReturn(TEST_PRODUCT);

        CreateProductResponseView product = productService.create(CREATE_PRODUCT_REQUEST_DTO);

        assertThat(product.getName()).isEqualTo(TEST_PRODUCT_NAME);
        verify(productRepository).save(any());
    }

    @DisplayName("품목 생성 실패 시 예외가 던져지는지")
    @Test
    public void createFail() {
        when(productRepository.save(any())).thenReturn(null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            productService.create(CREATE_PRODUCT_REQUEST_DTO);
        });
    }

    @DisplayName("품목들이 조회되는지")
    @Test
    public void list() {
        when(productRepository.findAll()).thenReturn(LIST_PRODUCT_RESPONSE_VIEW);

        ListProductResponseView products = productService.list();

        assertThat(products.getSize()).isGreaterThan(1);
        verify(productRepository).findAll();
    }

    @DisplayName("품목 상세 내역이 조회되는지")
    @Test
    public void find() {
        when(productRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(TEST_PRODUCT));

        FindProductResponseView product = productService.findById(DEFAULT_ID);

        assertThat(product.getName()).isEqualTo(TEST_PRODUCT_NAME);
        verify(productRepository).findById(any());
    }

    @DisplayName("품목 이미지들이 저장되는지")
    @Test
    public void storeProductImagesTest() {
        productService.storeProductImages(CREATE_PRODUCT_REQUEST_DTO);

        //then
        assertThat(fileSystemStorageService.load(TEST_IMAGE_FILE_NAME1)).exists();
    }

    @DisplayName("product id directory 내의 있는 모든 파일을 가져오는지")
    @Test
    public void loadAllProductImagesInProductIdTest() {
        Stream<Path> pathStream = productService.loadAllProductImagesInProductId(DEFAULT_ID);

        //then
        assertThat(pathStream).hasSize(2);
    }

}
