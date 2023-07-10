package panomete.judsue.bill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import panomete.judsue.bill.entity.BillLocation;

public interface BillLocationRepository extends JpaRepository<BillLocation,Long> {
}
