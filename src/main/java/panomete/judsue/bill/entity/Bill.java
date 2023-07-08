package panomete.judsue.bill.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    String description;

    @Enumerated(EnumType.STRING)
    BillStatus status;

    @OneToOne
    BillLocation location;


    @ManyToMany(cascade = CascadeType.ALL)
    List<ItemList> itemLists;
}
