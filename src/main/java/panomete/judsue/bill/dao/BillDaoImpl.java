package panomete.judsue.bill.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import panomete.judsue.bill.entity.Bill;
import panomete.judsue.bill.repository.BillRepository;
import panomete.judsue.security.entity.Users;

import java.util.List;

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
    public Page<Bill> getBillsByUser(Users user, PageRequest pageRequest) {
        return billRepository.findAllByUserAndIsActiveTrue(user,pageRequest);
    }

    @Override
    public Bill getBillByUser(Long id, Users user) {
        return billRepository.findByIdAndIsActiveTrueAndUser(id,user);
    }

    @Override
    public Page<Bill> getBills(PageRequest pageRequest) {
        return billRepository.findAll(pageRequest);
    }
}