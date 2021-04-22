package reuse.domain;

import lombok.Getter;

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
}
