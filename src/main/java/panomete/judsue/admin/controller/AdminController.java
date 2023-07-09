package panomete.judsue.admin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/")
@Tag(name = "Admin API", description = "Admin API")
@SecurityRequirement(name = "Bearer Authentication")
public class AdminController {
    @PatchMapping("/{uuid}")
    @Operation(summary = "assign role")
    public ResponseEntity<?> assignRole(@PathVariable("uuid") String uuid) {
        return null;
    }
}
