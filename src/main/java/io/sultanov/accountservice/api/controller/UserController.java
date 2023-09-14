package io.sultanov.accountservice.api.controller;

import io.sultanov.accountservice.domain.auth.AuthService;
import io.sultanov.accountservice.domain.auth.LoginRequest;
import io.sultanov.accountservice.domain.user.User;
import io.sultanov.accountservice.domain.user.UserService;
import jakarta.security.auth.message.AuthException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/signup")
    public User createUser(@Valid @RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequest loginRequest) throws AuthException {
        return authService.login(loginRequest);
    }
}
