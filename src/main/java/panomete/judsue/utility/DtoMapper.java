package panomete.judsue.utility;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import panomete.judsue.product.entity.Product;
import panomete.judsue.product.payload.response.ProductDto;

@Mapper
public interface DtoMapper {
    DtoMapper INSTANCE = Mappers.getMapper(DtoMapper.class);

    @Mapping(target = "price", expression = "java(product.getPrice().toString())")
    ProductDto toProductDto(Product product);
}
