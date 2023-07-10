package panomete.judsue.approver.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import panomete.judsue.bill.entity.Bill;

public interface ApproverService {
    Bill approveBill(Long id);
    Bill rejectBill(Long id, String reason);

    Bill getBill(Long id);

    Page<Bill> getBills(PageRequest pageRequest);
}
