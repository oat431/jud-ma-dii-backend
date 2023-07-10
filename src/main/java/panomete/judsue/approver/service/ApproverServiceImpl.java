package panomete.judsue.approver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import panomete.judsue.bill.dao.BillDao;
import panomete.judsue.bill.entity.Bill;
import panomete.judsue.bill.entity.BillStatus;

@Service
@RequiredArgsConstructor
public class ApproverServiceImpl implements ApproverService {
    final BillDao billDao;

    @Override
    public Bill approveBill(Long id) {
        Bill bill = billDao.getBill(id);
        bill.setStatus(BillStatus.APPROVED);
        return billDao.saveBill(bill);
    }

    @Override
    public Bill rejectBill(Long id, String reason) {
        Bill bill = billDao.getBill(id);
        bill.setStatus(BillStatus.REJECTED);
        bill.setReason(reason);
        return billDao.saveBill(bill);
    }
}
