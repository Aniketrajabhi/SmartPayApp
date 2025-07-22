package org.example;

import com.wallet.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService service;

    public UserController(UserService service) { this.service = service; }

    record RegisterRequest(String email, String password, String fullName) {}
    record UserDto(Long id, String email, String fullName) {}

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody RegisterRequest req) {
        var user = service.createUser(req.email(), req.password(), req.fullName());
        return ResponseEntity.ok(new UserDto(user.getId(), user.getEmail(), user.getFullName()));
    }
}
