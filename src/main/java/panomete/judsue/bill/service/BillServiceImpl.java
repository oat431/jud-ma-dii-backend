package panomete.judsue.bill.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import panomete.judsue.bill.dao.BillDao;
import panomete.judsue.bill.entity.Bill;
import panomete.judsue.bill.payload.request.BillFilter;
import panomete.judsue.security.entity.Users;


@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService{
    final BillDao billDao;

    @Override
    public Bill getBill(Long id) {
        return billDao.getBill(id);
    }

    @Override
    public Page<Bill> getBills(PageRequest pageRequest) {
        return null;
    }

    @Override
    public Page<Bill> searchBills(BillFilter filter, PageRequest pageRequest) {
        return null;
    }

    @Override
    public Page<Bill> getBillsByUser(Users user, PageRequest pageRequest) {
        return billDao.getBillsByUser(user, pageRequest);
    }

    @Override
    public Bill getBillByUser(Long id, Users user) {
        return billDao.getBillByUser(id, user);
    }
}
