package panomete.judsue.utility;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import panomete.judsue.item.entity.Item;
import panomete.judsue.item.payload.response.ItemDto;
import panomete.judsue.security.entity.Users;
import panomete.judsue.security.payload.response.AuthDto;

import java.util.List;

@Mapper
public interface DtoMapper {
    DtoMapper INSTANCE = Mappers.getMapper(DtoMapper.class);

    @Mapping(target = "price", expression = "java(item.getPrice().toString())")
    ItemDto toProductDto(Item item);
    List<ItemDto> toProductDto(List<Item> items);

    @Mappings({
            @Mapping(target = "id", expression = "java(user.getUserId())"),
            @Mapping(target = "age", expression = "java(user.getAge())"),
            @Mapping(target = "name", expression = "java(user.getFullName())"),
            @Mapping(target = "role", expression = "java(user.getSimpleAuthorities())")
    })
    AuthDto toAuthDto(Users user);
}
