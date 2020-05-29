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

    @Builder
    public CreateBoardResponseView(Long id) {
        this.id = id;
    }

    public static CreateBoardResponseView toDto(Board savedBoard) {
        return CreateBoardResponseView.builder()
                .id(savedBoard.getId())
                .build();
    }
}
