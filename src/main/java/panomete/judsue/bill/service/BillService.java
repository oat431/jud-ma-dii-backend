package panomete.judsue.bill.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import panomete.judsue.bill.entity.Bill;
import panomete.judsue.bill.payload.request.BillFilter;
import panomete.judsue.bill.payload.request.BillRequest;
import panomete.judsue.security.entity.Users;

public interface BillService {
    Bill createBill(BillRequest request, Users requester);
    Bill updateBill(Long id, BillRequest request);
    Bill getBill(Long id);
    Bill deleteBill(Long id);
    Page<Bill> getBills(PageRequest pageRequest);
    Page<Bill> searchBills(BillFilter filter, PageRequest pageRequest);
}
