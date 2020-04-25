package reuse.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reuse.repository.ProductRepository;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static reuse.fixture.ProductFixture.TEST_PRODUCT;

@SpringBootTest
public class AbstractEntityTest {
    @Autowired
    private ProductRepository productRepository;

    @DisplayName("품목 생성 시 create 와 update 날자를 생성하는지")
    @Test
    public void createProduct() {
        //given
        LocalDateTime now = LocalDateTime.of(2020, 1, 20, 0, 0, 0);

        //when
        Product save = productRepository.save(TEST_PRODUCT);

        //then
        assertThat(save.getCreateAt()).isAfter(now);
        assertThat(save.getUpdateAt()).isAfter(now);
    }
}
