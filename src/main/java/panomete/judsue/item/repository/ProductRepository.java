package panomete.judsue.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import panomete.judsue.item.entity.Item;

public interface ProductRepository extends JpaRepository<Item,Long> {
}
