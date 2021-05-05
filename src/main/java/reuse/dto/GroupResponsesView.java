package reuse.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class GroupResponsesView {
    private List<GroupResponseView> group;

    public GroupResponsesView(List<GroupResponseView> group) {
        this.group = group;
    }
}
