package reuse.dto.favorite;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class CreateFavoriteBoardRequestView {
    @NotBlank(message = "board ID 는 필수 값입니다.")
    private Long boardId;

    @Builder
    public CreateFavoriteBoardRequestView(Long boardId) {
        this.boardId = boardId;
    }
}
