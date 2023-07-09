package panomete.judsue.bill.payload.request;

import java.util.List;

public record BillRequest(String name, String description, List<ItemList> items) {
}
