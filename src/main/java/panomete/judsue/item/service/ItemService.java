package panomete.judsue.item.service;

import panomete.judsue.item.entity.Item;

import java.util.List;

public interface ItemService {
    Item createItem(Item item);
    Item getItem(Long id);
    List<Item> getItems();
    List<Item> searchItems(String name);
    Item updateItem(Item item);
    Item deleteItem(Long id);
}
