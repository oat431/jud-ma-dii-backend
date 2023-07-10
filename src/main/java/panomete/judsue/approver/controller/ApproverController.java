package panomete.judsue.approver.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import panomete.judsue.approver.request.RejectRequest;
import panomete.judsue.approver.service.ApproverService;
import panomete.judsue.bill.entity.Bill;
import panomete.judsue.bill.payload.response.BillDto;
import panomete.judsue.bill.payload.response.PageBillDto;
import panomete.judsue.utility.DtoMapper;

import java.util.List;

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

    @GetMapping("/{bill_id}")
    @Operation(summary = "get bills details")
    public ResponseEntity<BillDto> getBillsAsApprover(@PathVariable("bill_id") Long id) {
        return new ResponseEntity<>(
                DtoMapper.INSTANCE.toBillDto(approverService.getBill(id)),
                HttpStatus.OK
        );
    }

    @GetMapping("/")
    @Operation(summary = "get all bills")
    public ResponseEntity<PageBillDto> getBillsAsPaginationAsApprover(
            @RequestParam(value = "_page", defaultValue = "1") int page,
            @RequestParam(value = "_size", defaultValue = "10") int size
    ) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<Bill> bills = approverService.getBills(pageRequest);
        List<BillDto> billList = DtoMapper.INSTANCE.toBillDto(bills.getContent());
        PageBillDto pageBillDto = new PageBillDto(
                billList,
                bills.getTotalPages(),
                bills.getTotalElements(),
                size,
                page
        );
        return new ResponseEntity<>(
                pageBillDto,
                HttpStatus.OK
        );
    }
}
