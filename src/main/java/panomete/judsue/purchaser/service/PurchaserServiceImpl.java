package panomete.judsue.purchaser.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import panomete.judsue.bill.dao.BillDao;
import panomete.judsue.bill.dao.BillLocationDao;
import panomete.judsue.bill.entity.Bill;
import panomete.judsue.bill.entity.BillLocation;
import panomete.judsue.bill.entity.BillStatus;
import panomete.judsue.bill.payload.request.BillLocationRequest;

@Service
@RequiredArgsConstructor
public class PurchaserServiceImpl implements PurchaserService {
    final BillDao billDao;
    final BillLocationDao billLocationDao;

    @Override
    public Page<Bill> getApprovedBills(PageRequest pageRequest) {
        return billDao.getBillsByStatus(pageRequest, BillStatus.APPROVED);
    }

    @Override
    public Page<Bill> getPurchasingBills(PageRequest pageRequest) {
        return billDao.getBillsByStatus(pageRequest, BillStatus.PURCHASING);
    }

    @Override
    public Bill getBill(Long id) {
        return billDao.getBill(id);
    }

    @Override
    public Bill purchasingItem(Long id) {
        Bill bill = billDao.getBill(id);
        bill.setStatus(BillStatus.PURCHASING);
        return billDao.saveBill(bill);
    }

    @Override
    public Bill purchasdItem(Long id, BillLocationRequest request) {
        Bill bill = billDao.getBill(id);
        bill.setStatus(BillStatus.DELIVERED);
        BillLocation location = BillLocation.builder()
                .address(request.address())
                .state(request.state())
                .city(request.city())
                .zip(request.zip())
                .build();
        billLocationDao.saveBillLocation(location);
        bill.setLocation(location);
        return billDao.saveBill(bill);
    }

    @Override
    public Bill cancelPurchasdItem(Long id) {
        Bill bill = billDao.getBill(id);
        bill.setStatus(BillStatus.CANCELED);
        return billDao.saveBill(bill);
    }
}
