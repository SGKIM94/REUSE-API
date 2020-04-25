package reuse.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reuse.dto.product.CreateProductResponseView;
import reuse.repository.ProductRepository;
import reuse.security.TokenAuthenticationService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static reuse.fixture.ProductFixture.*;

@SpringBootTest
public class ProductServiceTest {
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private TokenAuthenticationService tokenAuthenticationService;

    @BeforeEach
    void setUp() {
        this.tokenAuthenticationService = new TokenAuthenticationService();
        this.productService = new ProductService(productRepository);
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
}
