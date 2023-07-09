package panomete.judsue.bill.payload.response;

import panomete.judsue.item.payload.response.ItemDto;

public record ItemListDto(ItemDto item, Integer amount) {
}
