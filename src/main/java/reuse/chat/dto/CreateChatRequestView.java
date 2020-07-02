package reuse.chat.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateChatRequestView {
    private String name;

    @Builder
    public CreateChatRequestView(String name) {
        this.name = name;
    }
}
