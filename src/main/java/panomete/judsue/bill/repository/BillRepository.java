package panomete.judsue.bill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import panomete.judsue.bill.entity.Bill;

public interface BillRepository extends JpaRepository<Bill,Long> {
}
