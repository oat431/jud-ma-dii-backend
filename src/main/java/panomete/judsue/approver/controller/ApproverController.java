package panomete.judsue.approver.controller;

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
@RequestMapping("/api/v1/approver/")
@Tag(name = "Approver API", description = "Approver API")
public class ApproverController {
    @PatchMapping("/{bill_id}")
    @Operation(summary = "approve bill")
    public ResponseEntity<Bill> approveBill(@PathVariable("bill_id") Long id) {
        return ResponseEntity.ok().build();
    }
}
