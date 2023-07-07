package panomete.judsue.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import panomete.judsue.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
