package panomete.judsue.bill.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import panomete.judsue.bill.entity.Bill;
import panomete.judsue.bill.repository.BillRepository;

@Repository
@RequiredArgsConstructor
public class BillDaoImpl implements BillDao{
    final BillRepository billRepository;
    @Override
    public Bill saveBill(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public Bill getBill(Long id) {
        return billRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteBillById(Long id) {
        billRepository.deleteById(id);
    }

    @Override
    public Page<Bill> getBills(PageRequest pageRequest) {
        return billRepository.findAll(pageRequest);
    }
}
