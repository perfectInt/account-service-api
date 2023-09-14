package io.sultanov.accountservice.api.controller;

import io.sultanov.accountservice.domain.user.User;
import io.sultanov.accountservice.domain.user.UserDetailsImpl;
import io.sultanov.accountservice.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/empl")
public class EmployeeController {

    private final UserService userService;

    @GetMapping("/payment")
    public UserDetailsImpl getCurrentUser() {
        return userService.getCurrentUser();
    }
}
