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
public class FindBoardResponseView {
    private Long id;
    private String title;
    private String content;
    private Product product;
    private User seller;
    private String sellerAddress;
    private Board.SalesStatusType salesStatus;

    @Builder
    public FindBoardResponseView(Long id, String title, String content, Product product, User seller, String sellerAddress, Board.SalesStatusType salesStatus) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.product = product;
        this.seller = seller;
        this.sellerAddress = sellerAddress;
        this.salesStatus = salesStatus;
    }

    public static FindBoardResponseView toDto(Board board) {
        return FindBoardResponseView.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .product(board.getProduct())
                .seller(board.getSeller())
                .sellerAddress(board.getSellerAddress())
                .salesStatus(board.getSalesStatus())
                .build();
    }
}
