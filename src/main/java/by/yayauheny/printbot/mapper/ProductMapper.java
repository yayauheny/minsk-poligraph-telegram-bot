package by.yayauheny.printbot.mapper;

import by.yayauheny.printbot.dto.ProductCreateResponse;
import by.yayauheny.printbot.dto.ProductDto;
import by.yayauheny.printbot.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    @Mapping(target = "available", constant = "true")
    ProductEntity toEntity(ProductDto dto);

    ProductCreateResponse toCreateResponse(ProductEntity entity);

    ProductDto toDto(ProductEntity entity);

    List<ProductDto> toDtoList(List<ProductEntity> entities);

    void update(@MappingTarget ProductEntity entity, ProductDto dto);
}
