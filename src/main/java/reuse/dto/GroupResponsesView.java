package reuse.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GroupResponsesView {
    private List<GroupResponseView> group;

    public GroupResponsesView(List<GroupResponseView> group) {
        this.group = group;
    }
}
