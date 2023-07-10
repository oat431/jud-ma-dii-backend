package panomete.judsue.bill.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import panomete.judsue.bill.entity.Bill;
import panomete.judsue.bill.payload.request.BillFilter;
import panomete.judsue.bill.payload.request.BillRequest;
import panomete.judsue.security.entity.Users;

import java.util.List;

public interface BillService {
    // global bill pool
    Bill getBill(Long id);
    Page<Bill> getBills(PageRequest pageRequest);
    Page<Bill> searchBills(BillFilter filter, PageRequest pageRequest);

    // requester only pool
    Page<Bill> getBillsByUser(Users user, PageRequest pageRequest);
    Bill getBillByUser(Long id, Users user);
}
