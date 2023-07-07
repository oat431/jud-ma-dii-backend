package panomete.judsue.security.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public record LoginRequest (
    String username,
    String email,
    String password
) { }
