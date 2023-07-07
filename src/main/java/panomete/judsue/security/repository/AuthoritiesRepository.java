package panomete.judsue.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import panomete.judsue.security.entity.Authorities;
import panomete.judsue.security.entity.Roles;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Long> {
    Authorities findByName(Roles name);
}
