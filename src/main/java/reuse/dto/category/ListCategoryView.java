package reuse.dto.category;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reuse.domain.ManufacturerGroup;
import reuse.domain.ModelGroup;
import reuse.domain.TelocoGroup;
import reuse.dto.GroupResponsesView;

@Getter
@Setter
@NoArgsConstructor
public class ListCategoryView {
    private GroupResponsesView models;
    private GroupResponsesView telecos;
    private GroupResponsesView manufacturers;

    @Builder
    public ListCategoryView(GroupResponsesView models, GroupResponsesView telecos, GroupResponsesView manufacturers) {
        this.models = models;
        this.telecos = telecos;
        this.manufacturers = manufacturers;
    }

    public static ListCategoryView toDto() {
        return ListCategoryView.builder()
                .models(ModelGroup.toGroupDto())
                .manufacturers(ManufacturerGroup.toGroupDto())
                .telecos(TelocoGroup.toGroupDto())
                .build();
    }
}
