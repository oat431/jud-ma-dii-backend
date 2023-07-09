package panomete.judsue.bill.payload.response;

import java.util.List;

public record BillDto(
        String name,
        String description,
        List<ItemListDto> itemLists,
        String total,
        String createdBy,
        String status
) {
}
