package reuse.dto.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reuse.domain.Board;
import reuse.domain.Product;

@Getter
@Setter
@NoArgsConstructor
public class CreateBoardResponseView {
    private Long id;
    private String title;
    private String content;
    private Product product;
    private String sellerAddress;

    @Builder
    public CreateBoardResponseView(Long id, String title, String content, Product product, String sellerAddress) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.product = product;
        this.sellerAddress = sellerAddress;
    }

    public static CreateBoardResponseView toDto(Board savedBoard) {
        return CreateBoardResponseView.builder()
                .id(savedBoard.getId())
                .title(savedBoard.getTitle())
                .content(savedBoard.getContent())
                .product(savedBoard.getProduct())
                .sellerAddress(savedBoard.getSellerAddress())
                .build();
    }
}
