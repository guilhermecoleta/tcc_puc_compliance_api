package puc.tcc.compliance.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import puc.tcc.compliance.api.persistence.domain.DocumentEntity;
import puc.tcc.compliance.api.resources.document.DocumentRequest;
import puc.tcc.compliance.api.resources.document.DocumentResponse;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DocumentMapper {

    @Mappings({
            @Mapping(source = "request.id", target = "id"),
            @Mapping(source = "request.name", target = "name"),
            @Mapping(source = "request.text", target = "text")
    })
    DocumentEntity toModel(DocumentRequest request);

    @Mappings({
            @Mapping(source = "entity.id", target = "id"),
            @Mapping(source = "entity.name", target = "name"),
            @Mapping(source = "entity.text", target = "text"),
            @Mapping(source = "entity.version", target = "version"),
            @Mapping(source = "entity.datUpdated", target = "datUpdated")
    })
    DocumentResponse toResponse(DocumentEntity entity);

}
