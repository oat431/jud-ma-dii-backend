package panomete.judsue.bill.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import panomete.judsue.bill.entity.ItemList;
import panomete.judsue.bill.repository.ItemListRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemListDaoImpl implements ItemListDao{
    final ItemListRepository itemListRepository;

    @Override
    public ItemList saveItemList(ItemList itemList) {
        return itemListRepository.save(itemList);
    }

    @Override
    public ItemList saveAllItemLists(List<ItemList> itemLists) {
        return itemListRepository.saveAll(itemLists).get(0);
    }

    @Override
    public ItemList getItemListById(Long id) {
        return itemListRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteItemListById(Long id) {
        itemListRepository.deleteById(id);
    }
}
