package panomete.judsue.bill.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import panomete.judsue.bill.entity.Bill;
import panomete.judsue.bill.entity.BillStatus;
import panomete.judsue.security.entity.Users;

public interface BillDao {
    // global bill pool
    Bill saveBill(Bill bill);
    Bill getBill(Long id);
    Page<Bill> getBills(PageRequest pageRequest);

    // requester only pool
    Page<Bill> getBillsByUser(Users user, PageRequest pageRequest);
    Bill getBillByUser(Long id, Users user);

    // purchaser only pool
    Page<Bill> getBillsByStatus(PageRequest pageRequest, BillStatus status);
    Bill getOnlyApprovedBill(Long id);

    Page<Bill> getOnlyWaitngBills(PageRequest pageRequest);
    Bill getOnlyWaitngBill(Long id);
}
