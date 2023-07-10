package panomete.judsue.bill.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import panomete.judsue.bill.entity.Bill;
import panomete.judsue.bill.payload.response.BillDto;
import panomete.judsue.bill.payload.response.PageBillDto;
import panomete.judsue.bill.service.BillService;
import panomete.judsue.utility.DtoMapper;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/bill")
@Tag(name = "Bill API", description = "Bill API")
@SecurityRequirement(name = "Bearer Authentication")
public class BillController {
    final BillService billService;
    @GetMapping("/{id}")
    @Operation(summary = "get bills details")
    public ResponseEntity<BillDto> getBills(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                DtoMapper.INSTANCE.toBillDto(billService.getBill(id)),
                HttpStatus.OK
        );
    }

    @GetMapping("/")
    @Operation(summary = "get all bills")
    public ResponseEntity<PageBillDto> getBillsAsPagination(
            @RequestParam(value = "_page", defaultValue = "1") int page,
            @RequestParam(value = "_size", defaultValue = "10") int size
    ) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<Bill> bills = billService.getBills(pageRequest);
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
