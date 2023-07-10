package panomete.judsue.approver.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import panomete.judsue.approver.request.RejectRequest;
import panomete.judsue.approver.service.ApproverService;
import panomete.judsue.bill.entity.Bill;
import panomete.judsue.bill.payload.response.BillDto;
import panomete.judsue.utility.DtoMapper;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/approver/")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Approver API", description = "Approver API")
public class ApproverController {
    final ApproverService approverService;

    @PatchMapping("/{bill_id}")
    @Operation(summary = "approve bill")
    public ResponseEntity<BillDto> approveBill(
            @PathVariable("bill_id") Long id,
            @RequestParam Boolean approved,
            @RequestBody(required = false) RejectRequest reason
    ) {
        Bill bill = approved ? approverService.approveBill(id) : approverService.rejectBill(id, reason.reason());
        return new ResponseEntity<>(
                DtoMapper.INSTANCE.toBillDto(bill),
                HttpStatus.OK
        );
    }
}
