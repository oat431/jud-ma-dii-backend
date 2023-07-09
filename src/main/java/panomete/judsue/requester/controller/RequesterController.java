package panomete.judsue.requester.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import panomete.judsue.bill.entity.Bill;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/requester/")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Requester API", description = "Requester API")
public class RequesterController {
    @PostMapping("/")
    @Operation(summary = "create bill")
    public ResponseEntity<Bill> createBill() {
        return null;
    }
}
