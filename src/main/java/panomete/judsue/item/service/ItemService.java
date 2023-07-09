package panomete.judsue.item.service;

import panomete.judsue.item.entity.Item;
import panomete.judsue.item.payload.request.ItemRequest;

import java.util.List;

public interface ItemService {
    Item createItem(ItemRequest item);
    Item getItem(Long id);
    List<Item> getItems();
    List<Item> searchItems(String name);
    Item updateItem(Long id, ItemRequest item);
    Item deleteItem(Long id);
}
