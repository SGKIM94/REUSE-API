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
    private String title;
    private String content;
    private Long productId;
    private String sellerAddress;

    @Builder
    public CreateBoardRequestView(String title, String content, Long productId, String sellerAddress) {
        this.title = title;
        this.content = content;
        this.productId = productId;
        this.sellerAddress = sellerAddress;
    }

    public static Board toEntity(CreateBoardRequestView board, Product product, User seller) {
        return Board.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .product(product)
                .seller(seller)
                .sellerAddress(board.getSellerAddress())
                .build();
    }
}
