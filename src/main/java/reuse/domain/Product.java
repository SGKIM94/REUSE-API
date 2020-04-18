package reuse.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 20)
    private String name;

    @Size(min = 1, max = 200)
    private String explanation;

    private String price;

    private String tax;

    private LocalDateTime registerDate;
    private LocalDateTime updateDate;

    private Boolean isUsed;
    private Boolean isSold;

    private String sellId;
    private String categoryId;

    @Builder
    public Product(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
