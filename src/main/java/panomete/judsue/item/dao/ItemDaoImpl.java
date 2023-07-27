package panomete.judsue.item.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import panomete.judsue.item.entity.Item;
import panomete.judsue.item.repository.ItemRepository;

import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemDaoImpl implements ItemDao {
    final ItemRepository itemRepository;
    @Override
    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Item getItem(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    @Override
    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    @Override
    public List<Item> searchItems(String name) {
        return Collections.emptyList();
    }
}
