package reuse.dto.product;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FindProductRequestView {
    private Long id;

    @Builder
    public FindProductRequestView(Long id) {
        this.id = id;
    }
}
