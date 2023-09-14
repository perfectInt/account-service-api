package io.sultanov.accountservice.domain.auth;

import io.sultanov.accountservice.api.security.config.JwtService;
import io.sultanov.accountservice.handler.AuthMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthMapper authMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public String login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        var user = authMapper.selectUserByEmail(loginRequest.getEmail()).orElseThrow(RuntimeException::new);
        return jwtService.generateToken(user);
    }
}
