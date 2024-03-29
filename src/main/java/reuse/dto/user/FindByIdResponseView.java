package reuse.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import reuse.domain.User;

@Getter
@NoArgsConstructor
public class FindByIdResponseView {
    private Long id;
    private String socialTokenId;
    private String name;

    @Builder
    public FindByIdResponseView(Long id, String socialTokenId, String name) {
        this.id = id;
        this.socialTokenId = socialTokenId;
        this.name = name;
    }

    public static FindByIdResponseView toDto(User user) {
        return FindByIdResponseView.builder()
                .id(user.getId())
                .name(user.getName())
                .socialTokenId(user.getSocialTokenId())
                .build();
    }
}
