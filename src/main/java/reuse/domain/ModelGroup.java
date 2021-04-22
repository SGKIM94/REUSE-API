package reuse.domain;

import lombok.Getter;
import reuse.dto.GroupResponseView;
import reuse.dto.GroupResponsesView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum ModelGroup {
    IPHONE7("!", "아이폰7"),
    IPHONE8("2",  "아이폰8"),
    IPHONE9("3",  "아이폰9"),
    IPHONE10("4",  "아이폰10"),
    IPHONE11("11",  "아이폰11"),
    IPHONEX("12",  "아이폰X"),
    IPHONEXS("13",  "아이폰XS"),
    GALAXY("14",  "갤럭시7"),
    GALAXY8("15",  "갤럭시8"),
    GALAXY9("16",  "갤럭시9"),
    GALAXY10("17",  "갤럭시10");

    ModelGroup(String value, String name) {
        this.value = value;
        this.name = name;
    }

    private final String value;
    private final String name;

    public static GroupResponsesView toGroupDto() {
        List<GroupResponseView> groups = Arrays.stream(ModelGroup.values())
                .map(GroupResponseView::toDtoByModelGroup)
                .collect(Collectors.toList());

        return new GroupResponsesView(groups);
    }
}
