package panomete.judsue.bill.dao;

import panomete.judsue.bill.entity.BillLocation;

public interface BillLocationDao {
    BillLocation saveBillLocation(BillLocation billLocation);
    BillLocation getBillLocationById(Long id);
}
