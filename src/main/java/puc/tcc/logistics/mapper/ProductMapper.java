package puc.tcc.logistics.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import puc.tcc.logistics.persistence.domain.ProductEntity;
import puc.tcc.logistics.resources.product.ProductRequest;
import puc.tcc.logistics.resources.product.ProductResponse;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    @Mappings({
            @Mapping(source = "request.id", target = "id"),
            @Mapping(source = "request.description", target = "description"),
            @Mapping(source = "request.name", target = "name"),
            @Mapping(source = "request.supplier", target = "supplier.id")
    })
    ProductEntity toModel(ProductRequest request);

    @Mappings({
            @Mapping(source = "entity.id", target = "id"),
            @Mapping(source = "entity.description", target = "description"),
            @Mapping(source = "entity.name", target = "name"),
            @Mapping(source = "entity.supplier.id", target = "supplier")
    })
    ProductResponse toResponse(ProductEntity entity);
}
