package converter;

import dto.BaseDTO;
import model.BaseEntity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class BaseConverter<Id extends Serializable, Model extends BaseEntity<Id>, DTO extends BaseDTO>
        implements Converter<Id, Model, DTO> {


    public Set<Id> convertModelsToIDs(Set<Model> models) {
        return models.stream()
                .map(model -> model.getId())
                .collect(Collectors.toSet());
    }

    public Set<Id> convertDTOsToIDs(Set<DTO> dtos) {
        return dtos.stream()
                .map(dto -> (Id)dto.getId())
                .collect(Collectors.toSet());
    }

    public Set<DTO> convertModelsToDTOs(Collection<Model> models) {
        return models.stream()
                .map(model -> convertModelToDTO(model))
                .collect(Collectors.toSet());
    }
}