package panomete.judsue.requester.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import panomete.judsue.bill.entity.Bill;
import panomete.judsue.bill.payload.request.BillRequest;
import panomete.judsue.security.entity.Users;

public interface RequesterService {
    Bill createBill(BillRequest bill, Users user);
    Bill getBillByUser(Long id, Users user);
    Bill updateBill(Long id, BillRequest bill);
    Bill deleteBill(Long id);
    Page<Bill> getBillsByUser(Users user, PageRequest pageRequest);


}
