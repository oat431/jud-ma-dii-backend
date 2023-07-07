package panomete.judsue.security.service;


import panomete.judsue.security.entity.Users;
import panomete.judsue.security.payload.request.RegisterRequest;
import panomete.judsue.security.payload.request.UpdateRequest;

import java.util.UUID;

public interface AuthService {
    Users getUserByUsername(String username);
    Users getUserByEmail(String email);
    Users getUserById(UUID id);
    Users createUser(RegisterRequest user);
    Users updateUser(UUID userId, UpdateRequest user);
}
