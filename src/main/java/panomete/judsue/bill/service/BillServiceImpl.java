package panomete.judsue.bill.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import panomete.judsue.bill.dao.BillDao;
import panomete.judsue.bill.dao.ItemListDao;
import panomete.judsue.bill.entity.Bill;
import panomete.judsue.bill.entity.ItemList;
import panomete.judsue.bill.payload.request.BillFilter;
import panomete.judsue.bill.payload.request.BillRequest;
import panomete.judsue.item.dao.ItemDao;
import panomete.judsue.security.dao.AuthDao;
import panomete.judsue.security.entity.Users;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService{
    final BillDao billDao;
    final ItemListDao itemListDao;
    final ItemDao itemDao;
    final AuthDao authDao;

    @Override
    @Transactional
    public Bill createBill(BillRequest request, Users requester) {
        Bill bill = Bill.builder()
                .name(request.name())
                .description(request.description())
                .itemLists(new ArrayList<>())
                .user(requester)
                .build();
        billDao.saveBill(bill);
        return getItemList(request, bill);
    }

    @Override
    @Transactional
    public Bill updateBill(Long id, BillRequest request) {
        Bill bill = billDao.getBill(id);
        bill.setName(request.name());
        bill.setDescription(request.description());
        bill.getItemLists().stream().forEach(itemList -> itemListDao.deleteItemListById(itemList.getId()));
        bill.setItemLists(new ArrayList<>());
        return getItemList(request, bill);
    }

    @Transactional
    public Bill getItemList(BillRequest request, Bill bill) {
        List<ItemList> itemLists = new ArrayList<>();
        request.items().forEach(itemListRequest -> {
            ItemList itemList = ItemList.builder()
                    .item(itemDao.getItem(itemListRequest.itemId()))
                    .amount(itemListRequest.amount())
                    .bill(bill)
                    .build();
            itemLists.add(itemList);
        });
        itemListDao.saveAllItemLists(itemLists);
        return billDao.saveBill(bill);
    }

    @Override
    public Bill getBill(Long id) {
        return billDao.getBill(id);
    }

    @Override
    public Bill deleteBill(Long id) {
        Bill bill = billDao.getBill(id);
        bill.setIsActive(false);
        return billDao.saveBill(bill);
    }

    @Override
    public Page<Bill> getBills(PageRequest pageRequest) {
        return null;
    }

    @Override
    public Page<Bill> searchBills(BillFilter filter, PageRequest pageRequest) {
        return null;
    }

    @Override
    public Page<Bill> getBillsByUser(Users user, PageRequest pageRequest) {
        return billDao.getBillsByUser(user, pageRequest);
    }

    @Override
    public Bill getBillByUser(Long id, Users user) {
        return billDao.getBillByUser(id, user);
    }
}
