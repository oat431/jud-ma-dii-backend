package panomete.judsue.purchaser.controller;

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
import panomete.judsue.bill.entity.Bill;
import panomete.judsue.bill.entity.BillStatus;
import panomete.judsue.bill.payload.request.BillLocationRequest;
import panomete.judsue.bill.payload.response.BillDto;
import panomete.judsue.bill.payload.response.PageBillDto;
import panomete.judsue.purchaser.request.PurchaserStatus;
import panomete.judsue.purchaser.service.PurchaserService;
import panomete.judsue.utility.DtoMapper;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/purchaser")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Purchaser API", description = "Purchaser API")
public class PurchaserController {
    final PurchaserService purchaserService;

    @PostMapping("/{bill_id}")
    @Operation(summary = "purchase bill")
    public ResponseEntity<BillDto> purchaseBill(
            @PathVariable("bill_id") Long id,
            @RequestBody BillLocationRequest request,
            @RequestParam("action") PurchaserStatus status
    ) {
        Bill bill = switch (status) {
            case PURCHASING -> purchaserService.purchasingItem(id);
            case DELIVERED -> purchaserService.purchasdItem(id, request);
            case CANCELED -> purchaserService.cancelPurchasdItem(id);
            default -> null;
        };
        return new ResponseEntity<>(
                DtoMapper.INSTANCE.toBillDto(bill),
                HttpStatus.OK
        );
    }

    @GetMapping("/")
    @Operation(summary = "get all bills approved")
    public ResponseEntity<PageBillDto> getBillsAsPurchaser(
            @RequestParam(value = "_page", defaultValue = "1") int page,
            @RequestParam(value = "_size", defaultValue = "10") int size,
            @RequestParam(value = "_status", defaultValue = "APPROVED") BillStatus status
    ) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
       Page<Bill> bills = switch (status) {
            case APPROVED -> purchaserService.getApprovedBills(pageRequest);
            case PURCHASING -> purchaserService.getPurchasingBills(pageRequest);
            default -> null;
        };
       if(bills == null) {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
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

    @GetMapping("/{bill_id}")
    @Operation(summary = "get bills details")
    public ResponseEntity<BillDto> getBillAsPurchaser(
            @PathVariable("bill_id") Long id
    ) {
        Bill bill = purchaserService.getBill(id);
        return new ResponseEntity<>(
                DtoMapper.INSTANCE.toBillDto(bill),
                HttpStatus.OK
        );
    }
}
