package panomete.judsue.utility;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import panomete.judsue.bill.entity.Bill;
import panomete.judsue.bill.payload.response.BillDto;
import panomete.judsue.item.entity.Item;
import panomete.judsue.item.payload.response.ItemDto;
import panomete.judsue.security.entity.Users;
import panomete.judsue.security.payload.response.AuthDto;

import java.util.List;

@Mapper
public interface DtoMapper {
    DtoMapper INSTANCE = Mappers.getMapper(DtoMapper.class);

    @Mapping(target = "price", expression = "java(item.getPrice().toString())")
    ItemDto toItemDto(Item item);
    List<ItemDto> toItemDto(List<Item> item);

    @Mappings({
            @Mapping(target = "total", expression = "java(bill.calculateTotal())"),
            @Mapping(target = "createdBy", expression = "java(bill.getUser().getFullName())"),
            @Mapping(target = "status", expression = "java(bill.getStatus().toString())")
    })
    BillDto toBillDto(Bill bill);
    List<BillDto> toBillDto(List<Bill> bill);

    @Mappings({
            @Mapping(target = "id", expression = "java(user.getUserId())"),
            @Mapping(target = "age", expression = "java(user.getAge())"),
            @Mapping(target = "name", expression = "java(user.getFullName())"),
            @Mapping(target = "role", expression = "java(user.getSimpleAuthorities())")
    })
    AuthDto toAuthDto(Users user);
}
