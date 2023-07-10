package panomete.judsue.bill.entity;

public enum BillStatus {
    // after create bill
    WAITING,

    // only approver
    APPROVED,
    REJECTED,

    // only purchaster
    PURCHASING,
    CANCELED,
    DELIVERED
}
