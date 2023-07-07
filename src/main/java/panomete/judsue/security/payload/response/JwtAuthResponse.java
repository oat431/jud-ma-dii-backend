package panomete.judsue.security.payload.response;

public record JwtAuthResponse (
    String sub,
    String role,
    String created,
    String id,
    Long exp
) { }