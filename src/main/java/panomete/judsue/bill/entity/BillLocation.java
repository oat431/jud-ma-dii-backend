package panomete.judsue.bill.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillLocation implements Serializable {
    @Serial
    private static final long serialVersionUID = 20L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String address;
    String city;
    String state;

    @Builder.Default
    String country = "";

    String zip;

    @OneToOne(mappedBy = "location", cascade = CascadeType.ALL)
    Bill bill;
}
