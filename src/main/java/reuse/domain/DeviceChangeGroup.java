package reuse.domain;

import lombok.Getter;
import reuse.dto.GroupResponseView;
import reuse.dto.GroupResponsesView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum DeviceChangeGroup {
    USIM("1", "유심 기변"),
    CONFIRM("2", "확정 기변");

    DeviceChangeGroup(String value, String name) {
        this.value = value;
        this.name = name;
    }

    private String value;
    private String name;

    public static GroupResponsesView toGroupDto() {
        List<GroupResponseView> groups = Arrays.stream(DeviceChangeGroup.values())
                .map(GroupResponseView::toDtoByDeviceChange)
                .collect(Collectors.toList());

        return new GroupResponsesView(groups);
    }
}
