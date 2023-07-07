package panomete.judsue.security.payload.response;

public record LocationDto (
    String address,
    String city,
    String state,
    String country,
    String zip
) { }
