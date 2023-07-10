package panomete.judsue.bill.service;

import panomete.judsue.bill.entity.ItemList;
import panomete.judsue.bill.payload.request.ItemListRequest;

public interface ItemListService {
    ItemList createItemList(ItemListRequest request);
    ItemList updateItemList(Long id, ItemListRequest request);
    ItemList deleteItemList(Long id);
}
