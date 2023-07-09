package panomete.judsue.item.dao;

import panomete.judsue.item.entity.Item;

import java.util.List;

public interface ItemDao {
    Item saveItem(Item item);
    Item getItem(Long id);
    void deleteItem(Long id);
    List<Item> getItems();
    List<Item> searchItems(String name);
}
