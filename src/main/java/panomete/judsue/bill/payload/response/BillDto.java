package panomete.judsue.bill.payload.response;

import java.util.List;

public record BillDto(
        Long id,
        String name,
        String description,
        List<ItemListDto> itemLists,
        String total,
        String createdBy,
        String status,
        String reason
) {
}
