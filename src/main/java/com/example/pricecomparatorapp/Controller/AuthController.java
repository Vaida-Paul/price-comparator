package com.example.pricecomparatorapp.Controller;



import com.example.pricecomparatorapp.Dto.AuthRequest;
import com.example.pricecomparatorapp.Dto.AuthResponse;
import com.example.pricecomparatorapp.Dto.RegisterRequest;
import com.example.pricecomparatorapp.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Registers a new user.
     *
     * @param request The registration request containing user details.
     * @return A response entity containing the authentication response.
     */
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }
    /**
     * Logs in an existing user.
     *
     * @param request The login request containing username and password.
     * @return A response entity containing the authentication response.
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}