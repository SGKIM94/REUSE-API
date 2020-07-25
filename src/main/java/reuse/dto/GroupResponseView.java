package reuse.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reuse.domain.DeviceChangeGroup;
import reuse.domain.ManufacturerGroup;
import reuse.domain.ModelGroup;
import reuse.domain.TelecoGroup;

@Getter
@Setter
@NoArgsConstructor
public class GroupResponseView {
    private String value;
    private String name;

    @Builder
    public GroupResponseView(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public static GroupResponseView toDtoByModelGroup(ModelGroup model) {
        return GroupResponseView.builder()
                .value(model.getValue())
                .name(model.getName())
                .build();
    }

    public static GroupResponseView toDtoByManufacturer(ManufacturerGroup manufacturer) {
        return GroupResponseView.builder()
                .value(manufacturer.getValue())
                .name(manufacturer.getName())
                .build();
    }

    public static GroupResponseView toDtoByTeleco(TelecoGroup teleco) {
        return GroupResponseView.builder()
                .value(teleco.getValue())
                .name(teleco.getName())
                .build();
    }

    public static GroupResponseView toDtoByDeviceChange(DeviceChangeGroup deviceChange) {
        return GroupResponseView.builder()
                .value(deviceChange.getValue())
                .name(deviceChange.getName())
                .build();
    }
}
