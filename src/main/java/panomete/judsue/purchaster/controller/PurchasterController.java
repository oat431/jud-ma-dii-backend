package panomete.judsue.purchaster.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/purchaster")
@Tag(name = "Purchaster APi", description = "Purchaster API")
public class PurchasterController {
}
