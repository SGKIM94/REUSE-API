package reuse.dto.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ModifyBoardStatusRequestView {
    private Long id;

    @Builder
    public ModifyBoardStatusRequestView(Long id) {
        this.id = id;
    }

    public static ModifyBoardStatusRequestView toDto(Long id) {
        return ModifyBoardStatusRequestView.builder()
                .id(id)
                .build();
    }
}
