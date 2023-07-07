package panomete.judsue.security.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import panomete.judsue.security.entity.Users;

import java.util.UUID;

public interface AuthRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
    Users findByEmail(String email);
    Users findById(UUID id);
}
