package reuse.dto.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reuse.domain.Board;
import reuse.domain.SalesStatusType;
import reuse.domain.User;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class CreateBoardRequestView {
    @NotBlank(message = "제목은 필수 값입니다.")
    private String title;
    @NotBlank(message = "내용은 필수 값입니다.")
    private String content;
    @NotBlank(message = "품목 ID는 필수 값입니다.")
    private Long productId;
    @NotBlank(message = "판매자 주소는 필수 값입니다.")
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
