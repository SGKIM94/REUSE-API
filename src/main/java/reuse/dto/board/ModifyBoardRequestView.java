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
public class ModifyBoardRequestView {
    private Long id;
    private String title;
    private String content;
    private Product product;
    private String sellerAddress;

    @Builder
    public ModifyBoardRequestView(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.product = board.getProduct();
        this.sellerAddress = board.getSellerAddress();
    }

    public Board toEntity() {
        return Board.builder()
                .id(id)
                .content(content)
                .product(product)
                .sellerAddress(sellerAddress)
                .build();
    }
}
