package panomete.judsue.security.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import panomete.judsue.security.entity.Users;
import panomete.judsue.security.payload.request.LoginRequest;
import panomete.judsue.security.payload.request.RegisterRequest;
import panomete.judsue.security.payload.request.UpdateRequest;
import panomete.judsue.security.payload.response.JwtResponse;
import panomete.judsue.security.service.AuthService;
import panomete.judsue.utility.DtoMapper;
import panomete.judsue.utility.JwtTokenUtil;

import java.util.UUID;

@Controller
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Authentication API", description = "the authentication API")
public class AuthController {
    final AuthService authService;
    final AuthenticationManager authenticationManager;
    final JwtTokenUtil jwtTokenUtil;
    final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /*
     * todo: implement auth controller by
     * - /auth/forgot-password (PATCH)
     * - /auth/reset-password (PATCH)
     * - /auth/email (PATCH)
     * - /auth/username (PATCH)
     */
    @PostMapping("/")
    @Operation(summary = "Login", description = "Login")
    public ResponseEntity<?> login(@RequestBody LoginRequest login) {
        if (login.username() == null) {
            return createToken(loginWithEmail(login.email(), login.password()));
        }
        if (login.email() == null) {
            return createToken(loginWithUsername(login.username(), login.password()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("username or email are missing");
    }

    private ResponseEntity<?> createToken(Users user) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
        JwtResponse jwtResponse = new JwtResponse(jwtTokenUtil.generateJWT(user, 604800L));
        return ResponseEntity.ok(jwtResponse);
    }

    private Users loginWithUsername(String username, String password) {
        Users user = authService.getUserByUsername(username);
        return loginProcess(username, password, user);
    }

    private Users loginWithEmail(String email, String password) {
        Users user = authService.getUserByEmail(email);
        return loginProcess(email, password, user);
    }

    private Users loginProcess(String username, String password, Users user) {
        if (!checkAuth(user, password)) {
            return null;
        }
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            log.info("user {} successfully logged in", username);
        } catch (Exception e) {
            log.error("login process error: {}", e.getMessage());
        }
        return user;
    }

    private boolean checkAuth(Users user, String password) {
        return user != null && passwordEncoder.matches(password, user.getPassword());
    }

    @PostMapping("/signup")
    @Operation(summary = "Create a new user", description = "Create a new user")
    public ResponseEntity<?> createUserAccount(@RequestBody RegisterRequest user) {
        Users newAccount = authService.createUser(user);
        return ResponseEntity.ok(
                DtoMapper.INSTANCE.toAuthDto(newAccount)
        );
    }

    @GetMapping("/details")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Get user details", description = "Get user details")
    public ResponseEntity<?> getUserDetails() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user = authService.getUserByUsername(auth.getName());
        return ResponseEntity.ok(
                DtoMapper.INSTANCE.toAuthDto(user)
        );
    }

    @GetMapping("/refresh")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Refresh token", description = "Refresh token")
    public ResponseEntity<?> refreshToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String refreshedToken = jwtTokenUtil.refreshJWT(token, 604800L);
        return ResponseEntity.ok(
                new JwtResponse(refreshedToken)
        );
    }

    @GetMapping("/credentials")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Get user credentials", description = "Get user credentials")
    public ResponseEntity<?> getUserCredentials(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        return ResponseEntity.ok(
                jwtTokenUtil.getClaimsFromToken(token)
        );
    }

    @PutMapping("/")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Update user details", description = "Update user details")
    public ResponseEntity<?> updateUserDetails(@RequestBody UpdateRequest update) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UUID userId = authService.getUserByUsername(auth.getName()).getId();
        Users updated = authService.updateUser(userId, update);
        return ResponseEntity.ok(
                DtoMapper.INSTANCE.toAuthDto(updated)
        );
    }
}
