package panomete.judsue.bill.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import panomete.judsue.bill.entity.Bill;

public interface BillDao {
    Bill saveBill(Bill bill);
    Bill getBill(Long id);
    void deleteBillById(Long id);
    Page<Bill> getBills(PageRequest pageRequest);
}
