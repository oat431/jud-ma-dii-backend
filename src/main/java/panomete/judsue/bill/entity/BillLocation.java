package panomete.judsue.bill.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String address;
    String city;
    String state;

    @Builder.Default
    String country = "Thailand";

    String zip;

    @OneToOne(mappedBy = "location", cascade = CascadeType.ALL)
    Bill bill;
}
