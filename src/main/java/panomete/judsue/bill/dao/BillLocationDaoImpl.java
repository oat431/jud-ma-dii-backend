package panomete.judsue.bill.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import panomete.judsue.bill.entity.BillLocation;
import panomete.judsue.bill.repository.BillLocationRepository;

@Repository
@RequiredArgsConstructor
public class BillLocationDaoImpl implements BillLocationDao {
    final BillLocationRepository billLocationRepository;

    @Override
    public BillLocation saveBillLocation(BillLocation billLocation) {
        return billLocationRepository.save(billLocation);
    }

    @Override
    public BillLocation getBillLocationById(Long id) {
        return billLocationRepository.findById(id).orElse(null);
    }
}
