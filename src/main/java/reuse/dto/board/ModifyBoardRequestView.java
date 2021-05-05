package reuse.dto.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import reuse.domain.Board;
import reuse.domain.Product;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class ModifyBoardRequestView {
    @NotBlank(message = "게시판 ID 필수 값입니다.")
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
                .content(content)
                .product(product)
                .sellerAddress(sellerAddress)
                .build();
    }
}
