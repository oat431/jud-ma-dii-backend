package panomete.judsue.bill.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import panomete.judsue.security.entity.Users;

import java.math.BigDecimal;
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
    @Builder.Default
    BillStatus status = BillStatus.WAITING;

    @OneToOne
    BillLocation location;

    String reason;

    @Builder.Default
    Boolean isActive = true;

    @ManyToOne
    Users user;


    @OneToMany(mappedBy = "bill",cascade = CascadeType.ALL)
    List<ItemList> itemLists;

    public String calculateTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (ItemList itemList : itemLists) {
            BigDecimal itemTotal = new BigDecimal(itemList.amount).multiply(itemList.item.getPrice());
            total = total.add(itemTotal);
        }
        return total.toString();
    }
}
