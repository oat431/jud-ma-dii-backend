package panomete.judsue.bill.payload.response;

public record BillLocationDto(
        String address,
        String city,
        String state,
        String country,
        String zip
) {
}
