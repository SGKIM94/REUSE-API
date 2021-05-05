package reuse.dto.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import reuse.domain.Board;

@Getter
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
