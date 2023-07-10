package panomete.judsue.bill.payload.response;

import java.util.List;

public record PageBillDto(
        List<BillDto> content,
        Integer totalPages,
        Long totalElements,
        Integer size,
        Integer page
) {
}
