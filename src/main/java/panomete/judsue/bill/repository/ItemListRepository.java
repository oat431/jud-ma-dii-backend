package panomete.judsue.bill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import panomete.judsue.bill.entity.ItemList;

import java.util.List;

public interface ItemListRepository extends JpaRepository<ItemList,Long> {
}
