package reuse.domain;

import lombok.Getter;
import reuse.dto.GroupResponseView;
import reuse.dto.GroupResponsesView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum ManufacturerGroup {
    SAMSUNG("1", "삼성"),
    LG("2", "LG"),
    APPLE("3", "애플"),
    BLACKBERRY("4", "블랙베리"),
    HAWAY("5", "하웨이"),
    XIOMING("6", "샤오밍");

    ManufacturerGroup(String value, String name) {
        this.value = value;
        this.name = name;
    }

    private String value;
    private String name;

    public static GroupResponsesView toGroupDto() {
        List<GroupResponseView> groups = Arrays.stream(ManufacturerGroup.values())
                .map(GroupResponseView::toDtoByManufacturer)
                .collect(Collectors.toList());

        return new GroupResponsesView(groups);
    }
}
