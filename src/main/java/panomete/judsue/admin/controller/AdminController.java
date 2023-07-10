package panomete.judsue.admin.controller;

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
import panomete.judsue.admin.service.AdminService;
import panomete.judsue.security.entity.Roles;
import panomete.judsue.security.entity.Users;
import panomete.judsue.security.payload.response.AuthDto;
import panomete.judsue.security.payload.response.PageAuthDto;
import panomete.judsue.utility.DtoMapper;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/")
@Tag(name = "Admin API", description = "Admin API")
@SecurityRequirement(name = "Bearer Authentication")
public class AdminController {
    final AdminService adminService;

    @PatchMapping("/{uuid}")
    @Operation(summary = "assign role")
    public ResponseEntity<AuthDto> assignRole(
            @PathVariable("uuid") String uuid,
            @RequestParam("role") Roles role
    ) {
        return new ResponseEntity<>(
                DtoMapper.INSTANCE.toAuthDto(adminService.assignRole(uuid, role)),
                HttpStatus.OK
        );
    }

    @GetMapping("/{uuid}")
    @Operation(summary = "get user by uuid")
    public ResponseEntity<AuthDto> getUser(
            @PathVariable("uuid") String uuid
    ) {
        return new ResponseEntity<>(
                DtoMapper.INSTANCE.toAuthDto(adminService.getUser(uuid)),
                HttpStatus.OK
        );
    }

    @GetMapping("/")
    @Operation(summary = "get all users")
    public ResponseEntity<PageAuthDto> getAllUsers(
            @RequestParam(value = "_page", defaultValue = "1") int page,
            @RequestParam(value = "_size", defaultValue = "10") int size,
            @RequestParam("_locked_account") boolean locked
    ) {
        Page<Users> users = locked ?
                adminService.getAllLockedUsers(PageRequest.of(page-1, size)) :
                adminService.getAllUsers(PageRequest.of(page-1, size));
        List<AuthDto> authList = DtoMapper.INSTANCE.toAuthDto(users.getContent());
        PageAuthDto pageAuthDto = new PageAuthDto(
                users.getTotalPages(),
                users.getTotalElements(),
                size,
                page,
                authList
        );
        return new ResponseEntity<>(
                pageAuthDto,
                HttpStatus.OK
        );
    }
}
