package reuse.dto.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reuse.domain.Board;
import reuse.domain.SalesStatusType;
import reuse.domain.User;

@Getter
@Setter
@NoArgsConstructor
public class CreateBoardRequestView {
    private String title;
    private String content;
    private Long productId;
    private String sellerAddress;
    private SalesStatusType salesStatus;

    @Builder
    public CreateBoardRequestView(String title, String content, Long productId, String sellerAddress) {
        this.title = title;
        this.content = content;
        this.productId = productId;
        this.sellerAddress = sellerAddress;
        this.salesStatus = SalesStatusType.SALE;
    }

    public static Board toEntity(CreateBoardRequestView board, User seller) {
        return Board.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .seller(seller)
                .sellerAddress(board.getSellerAddress())
                .salesStatus(SalesStatusType.SALE)
                .build();
    }
}
