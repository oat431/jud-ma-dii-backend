package panomete.judsue.bill.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import panomete.judsue.bill.entity.Bill;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/bill")
@Tag(name = "Bill API", description = "Bill API")
public class BillController {
    @GetMapping("/")
    @Operation(summary = "get bills as pagination")
    public ResponseEntity<Bill> getBills() {
        return ResponseEntity.ok().build();
    }
}
