package panomete.judsue.item.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import panomete.judsue.item.entity.Item;
import panomete.judsue.item.payload.request.ItemRequest;
import panomete.judsue.item.payload.response.ItemDto;
import panomete.judsue.item.service.ItemService;
import panomete.judsue.utility.DtoMapper;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/item")
@Tag(name = "Item API", description = "Item API")
@SecurityRequirement(name = "Bearer Authentication")
public class ItemController {
    final ItemService itemService;

    @GetMapping("/")
    @Operation(summary = "get items as list")
    public ResponseEntity<List<ItemDto>> getItems() {
        return new ResponseEntity<>(
                DtoMapper.INSTANCE.toItemDto(itemService.getItems()),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "get item by id")
    public ResponseEntity<ItemDto> getItemById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                DtoMapper.INSTANCE.toItemDto(itemService.getItem(id)),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    @Operation(summary = "create item")
    public ResponseEntity<ItemDto> createItem(@RequestBody ItemRequest item) {
        return new ResponseEntity<>(
                DtoMapper.INSTANCE.toItemDto(itemService.createItem(item)),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    @Operation(summary = "update item by id")
    public ResponseEntity<ItemDto> updateItem(@PathVariable("id") Long id, @RequestBody ItemRequest item) {
        return new ResponseEntity<>(
                DtoMapper.INSTANCE.toItemDto(itemService.updateItem(id, item)),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete item by id")
    public ResponseEntity<ItemDto> deleteItem(@PathVariable("id") Long id) {
        Item item = itemService.deleteItem(id);
        return new ResponseEntity<>(
                DtoMapper.INSTANCE.toItemDto(item),
                HttpStatus.OK
        );
    }
}
