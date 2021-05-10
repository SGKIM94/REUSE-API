package reuse.domain;

import lombok.Getter;
import reuse.dto.GroupResponseView;
import reuse.dto.GroupResponsesView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum TelecoGroup {
    SKT("1", "SKT"),
    KT("2", "KT"),
    LG("3", "LG"),
    SKT_MVNO("4", "SKT 알뜰폰"),
    KT_MVNO("5", "KT 알뜰폰"),
    LG_MVNO("6", "LG 알뜰폰");

    TelecoGroup(String value, String name) {
        this.value = value;
        this.name = name;
    }

    private final String value;
    private final String name;

    public static GroupResponsesView toGroupDto() {
        List<GroupResponseView> groups = Arrays.stream(TelecoGroup.values())
                .map(GroupResponseView::toDtoByTeleco)
                .collect(Collectors.toList());

        return new GroupResponsesView(groups);
    }
}
