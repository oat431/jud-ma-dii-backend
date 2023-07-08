package panomete.judsue.purchaster.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import panomete.judsue.bill.entity.Bill;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/purchaster")
@Tag(name = "Purchaster API", description = "Purchaster API")
public class PurchasterController {
    @PatchMapping("/{bill_id}")
    @Operation(summary = "purchase bill")
    public ResponseEntity<Bill> purchaseBill(@PathVariable("bill_id") Long id) {
        return ResponseEntity.ok().build();
    }

}
