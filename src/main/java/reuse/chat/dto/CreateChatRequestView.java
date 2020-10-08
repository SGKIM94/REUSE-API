package reuse.chat.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class CreateChatRequestView {
    @NotBlank(message = "채팅 방명은 필수 값입니다.")
    private String name;

    @Builder
    public CreateChatRequestView(String name) {
        this.name = name;
    }
}
