package panomete.judsue.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import panomete.judsue.security.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
