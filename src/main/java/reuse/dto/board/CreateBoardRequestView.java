package reuse.dto.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reuse.domain.Board;
import reuse.domain.Product;
import reuse.domain.User;

@Getter
@Setter
@NoArgsConstructor
public class CreateBoardRequestView {
    private Long id;
    private String title;
    private String content;
    private Long productId;
    private String sellerAddress;

    @Builder
    public CreateBoardRequestView(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.productId = board.getProductId();
        this.sellerAddress = board.getSellerAddress();
    }

    public static Board toEntity(CreateBoardRequestView board, Product product, User seller) {
        return Board.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .product(product)
                .seller(seller)
                .sellerAddress(board.getSellerAddress())
                .build();
    }
}
