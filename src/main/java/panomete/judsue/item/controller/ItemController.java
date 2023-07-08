package panomete.judsue.item.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/item")
@Tag(name = "Item API", description = "Item API")
public class ItemController {
    @GetMapping("/")
    @Operation(summary = "get items as list")
    public ResponseEntity<?> getItems() {
        return ResponseEntity.ok().build();
    }
}
