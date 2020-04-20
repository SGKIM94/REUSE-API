package reuse.dto.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import reuse.domain.*;

@Getter
@Setter
@NoArgsConstructor
public class CreateBoardRequestView {
    private Long id;
    private String title;
    private String content;
    private Product product;
    private String sellerAddress;

    @Builder
    public CreateBoardRequestView(Long id, String title, String content, Product product, String sellerAddress) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.product = product;
        this.sellerAddress = sellerAddress;
    }

    public static Board toEntity(CreateBoardRequestView board) {
        return Board.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .product(board.getProduct())
                .sellerAddress(board.getSellerAddress())
                .build();
    }
}
