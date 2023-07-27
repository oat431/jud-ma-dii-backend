package panomete.judsue.security.payload.request;

public record LoginRequest (
    String username,
    String email,
    String password
) { }
