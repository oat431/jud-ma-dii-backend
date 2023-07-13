package panomete.judsue.purchaser.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import panomete.judsue.bill.entity.Bill;
import panomete.judsue.bill.payload.request.BillLocationRequest;

public interface PurchaserService {
    Page<Bill> getApprovedBills(PageRequest pageRequest);
    Page<Bill> getPurchasingBills(PageRequest pageRequest);
    Bill getBill(Long id);
    Bill purchasingItem(Long id);
    Bill purchasdItem(Long id, BillLocationRequest request);
    Bill cancelPurchasdItem(Long id);
}
