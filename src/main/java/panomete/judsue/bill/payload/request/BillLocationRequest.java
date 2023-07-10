package panomete.judsue.bill.payload.request;

public record BillLocationRequest(
        String address,
        String city,
        String state,
        String country,
        String zip
) {
}
