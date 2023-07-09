package panomete.judsue.requester.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import panomete.judsue.bill.entity.Bill;
import panomete.judsue.bill.payload.request.BillRequest;
import panomete.judsue.bill.payload.response.BillDto;
import panomete.judsue.bill.service.BillService;
import panomete.judsue.security.service.AuthService;
import panomete.judsue.utility.DtoMapper;

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
}
