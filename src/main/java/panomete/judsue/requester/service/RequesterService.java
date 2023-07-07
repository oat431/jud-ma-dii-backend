package panomete.judsue.requester.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import panomete.judsue.bill.entity.Bill;
import panomete.judsue.bill.entity.ItemList;
import panomete.judsue.bill.payload.request.BillFilter;
import panomete.judsue.bill.payload.request.BillRequest;
import panomete.judsue.item.entity.Item;

import java.util.List;

public interface RequesterService {
    Bill createBill(BillRequest request);
    Bill updateBill(BillRequest request);
    Bill getBill(Long id);
    Bill deleteBill(Long id);
    Page<Bill> getBills(PageRequest pageRequest);
    Page<Bill> searchBills(BillFilter filter, PageRequest pageRequest);

    ItemList createItemList(ItemList itemList);
    ItemList updateItemList(ItemList itemList);
    ItemList deleteItemList(Long id);
    Page<ItemList> getItemLists(PageRequest pageRequest);

    List<Item> getItems();
}
