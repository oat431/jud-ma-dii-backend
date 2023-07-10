package panomete.judsue.bill.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import panomete.judsue.bill.entity.Bill;
import panomete.judsue.security.entity.Users;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill,Long> {
    Page<Bill> findAllByUserAndIsActiveTrue(Users user, Pageable pageable);
    Bill findByIdAndIsActiveTrueAndUser(Long id, Users user);
}
