package puc.tcc.logistics.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import puc.tcc.logistics.persistence.domain.SupplyEntity;
import puc.tcc.logistics.resources.supply.SupplyRequest;
import puc.tcc.logistics.resources.supply.SupplyResponse;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SupplyMapper {

    @Mappings({
            @Mapping(source = "request.id", target = "id"),
            @Mapping(source = "request.description", target = "description"),
            @Mapping(source = "request.name", target = "name")
    })
    SupplyEntity toModel(SupplyRequest request);

    @Mappings({
            @Mapping(source = "entity.id", target = "id"),
            @Mapping(source = "entity.description", target = "description")
    })
    SupplyResponse toResponse(SupplyEntity entity);
}
