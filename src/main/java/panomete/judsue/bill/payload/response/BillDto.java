package panomete.judsue.bill.payload.response;

import java.math.BigDecimal;
import java.util.List;

public record BillDto(String name, String description, List<ItemListDto> items, String total) {

    String calculateTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for(ItemListDto item : items) {
            BigDecimal eachPrice = new BigDecimal(item.item().price()).multiply(new BigDecimal(item.amount()));
            total = total.add(eachPrice);
        }
        return total.toString();
    }
}
