package reuse.dto.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ModifyBoardStatusRequestView {
    private Long id;

    @Builder
    public ModifyBoardStatusRequestView(Long id) {
        this.id = id;
    }
}
