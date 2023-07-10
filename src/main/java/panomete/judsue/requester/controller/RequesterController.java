package panomete.judsue.requester.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import panomete.judsue.bill.entity.Bill;
import panomete.judsue.bill.payload.request.BillRequest;
import panomete.judsue.bill.payload.response.BillDto;
import panomete.judsue.bill.payload.response.PageBillDto;
import panomete.judsue.bill.service.BillService;
import panomete.judsue.security.entity.Users;
import panomete.judsue.security.service.AuthService;
import panomete.judsue.utility.DtoMapper;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/requester/")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Requester API", description = "Requester API")
public class RequesterController {
    final BillService billService;
    final AuthService authService;
    @PostMapping("/")
    @Operation(summary = "create bill")
    public ResponseEntity<BillDto> createBill(@RequestBody BillRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Bill bill = billService.createBill(request, authService.getUserByUsername(auth.getName()));
        return ResponseEntity.ok(DtoMapper.INSTANCE.toBillDto(bill));
    }

    @GetMapping("/")
    @Operation(summary = "get bill of requester")
    public ResponseEntity<PageBillDto> getBill(
            @RequestParam(value = "_page", defaultValue = "1") int page,
            @RequestParam(value = "_size", defaultValue = "10") int size
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user = authService.getUserByUsername(auth.getName());
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<Bill> bills = billService.getBillsByUser(user, pageRequest);
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

    @GetMapping("/{id}")
    @Operation(summary = "get bill details only for requester")
    public ResponseEntity<BillDto> getBill(@PathVariable("id") Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user = authService.getUserByUsername(auth.getName());
        return new ResponseEntity<>(
                DtoMapper.INSTANCE.toBillDto(billService.getBillByUser(id, user)),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    @Operation(summary = "update bill")
    public ResponseEntity<BillDto> updateBill(@PathVariable("id") Long id, @RequestBody BillRequest request) {
        return new ResponseEntity<>(
                DtoMapper.INSTANCE.toBillDto(billService.updateBill(id, request)),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete bill")
    public ResponseEntity<BillDto> deleteBill(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                DtoMapper.INSTANCE.toBillDto(billService.deleteBill(id)),
                HttpStatus.OK
        );
    }
}
