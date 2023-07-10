package panomete.judsue.bill.dao;

import panomete.judsue.bill.entity.ItemList;

import java.util.List;

public interface ItemListDao {
    ItemList saveItemList(ItemList itemList);
    ItemList saveAllItemLists(List<ItemList> itemLists);
    ItemList getItemListById(Long id);
    void deleteItemListById(Long id);
//    List<ItemList> getItemListsByBillId(Long billId);
}
