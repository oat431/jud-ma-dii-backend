package panomete.judsue.purchaster.service;

import panomete.judsue.bill.entity.Bill;

public interface PurchasterService {
    Bill purchasdItem(Long id);
    Bill cancelPurchasdItem(Long id);
}
