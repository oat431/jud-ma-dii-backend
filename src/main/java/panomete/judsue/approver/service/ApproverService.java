package panomete.judsue.approver.service;

import panomete.judsue.bill.entity.Bill;

public interface ApproverService {
    Bill approveBill(Long id);
    Bill rejectBill(Long id);
}
