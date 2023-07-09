package panomete.judsue.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import panomete.judsue.item.dao.ItemDao;
import panomete.judsue.item.entity.Item;
import panomete.judsue.item.payload.request.ItemRequest;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    final ItemDao itemDao;
    @Override
    public Item createItem(ItemRequest item) {
        Item newItem = Item.builder()
                .name(item.name())
                .description(item.description())
                .price(new BigDecimal(item.price()))
                .build();
        return itemDao.saveItem(newItem);
    }

    @Override
    public Item getItem(Long id) {
        return itemDao.getItem(id);
    }

    @Override
    public List<Item> getItems() {
        return itemDao.getItems();
    }

    @Override
    public List<Item> searchItems(String name) {
        return itemDao.searchItems(name);
    }

    @Override
    public Item updateItem(Long id, ItemRequest item) {
        Item old = itemDao.getItem(id);
        if (old == null) {
            return null;
        }
        old.setName(item.name());
        old.setDescription(item.description());
        old.setPrice(new BigDecimal(item.price()));
        return itemDao.saveItem(old);
    }

    @Override
    public Item deleteItem(Long id) {
        Item old = itemDao.getItem(id);
        if (old == null) {
            return null;
        }
        itemDao.deleteItem(id);
        return old;
    }
}
