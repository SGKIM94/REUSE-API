package reuse.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class FindBySocialTokenIdResponseView {
    private Long idx;
    private String socialTokenId;
    private String socialType;

    public FindBySocialTokenIdResponseView(Long idx, String socialTokenId, String socialType) {
        this.idx = idx;
        this.socialTokenId = socialTokenId;
        this.socialType = socialType;
    }
}
