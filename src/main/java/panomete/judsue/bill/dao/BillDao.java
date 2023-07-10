package panomete.judsue.bill.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import panomete.judsue.bill.entity.Bill;
import panomete.judsue.security.entity.Users;

import java.util.List;

public interface BillDao {
    // global bill pool
    Bill saveBill(Bill bill);
    Bill getBill(Long id);
    Page<Bill> getBills(PageRequest pageRequest);

    // requester only pool
    Page<Bill> getBillsByUser(Users user, PageRequest pageRequest);
    Bill getBillByUser(Long id, Users user);

    // purchaser only pool
    Page<Bill> getOnlyApprovedBills(PageRequest pageRequest);
    Bill getOnlyApprovedBill(Long id);
}
