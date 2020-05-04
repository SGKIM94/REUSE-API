package reuse.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import reuse.domain.User;

import java.util.Optional;

@Getter
@Builder
@NoArgsConstructor
public class FindByIdResponseView {
    private Long id;
    private String email;
    private String name;

    @Builder
    public FindByIdResponseView(Long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public static FindByIdResponseView toDtoEntity(Optional<User> byId) {
        return new FindByIdResponseView();
    }

}
