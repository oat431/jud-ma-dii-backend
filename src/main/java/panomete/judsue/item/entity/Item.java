package panomete.judsue.item.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import panomete.judsue.bill.entity.ItemList;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    String name;
    String image;
    String description;

    BigDecimal price;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    List<ItemList> itemList;
}
