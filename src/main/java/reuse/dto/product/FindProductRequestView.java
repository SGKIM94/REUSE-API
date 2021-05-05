package reuse.dto.product;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FindProductRequestView {
    private Long id;

    @Builder
    public FindProductRequestView(Long id) {
        this.id = id;
    }
}
