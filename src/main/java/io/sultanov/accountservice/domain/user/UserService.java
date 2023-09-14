package io.sultanov.accountservice.domain.user;

import io.sultanov.accountservice.api.security.config.JwtService;
import io.sultanov.accountservice.api.security.utils.Role;
import io.sultanov.accountservice.handler.AuthMapper;
import io.sultanov.accountservice.handler.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthMapper authMapper;
    private final JwtService jwtService;

    public User createUser(User user) {
        user.setUserRole(Role.USER.getAuthority());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return getUserById(userMapper.insertUser(user));
    }

    public User getUserById(Long id) {
        return userMapper.selectUser(id);
    }

    public UserDetailsImpl getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authMapper.selectUserByEmail(authentication.getName()).orElseThrow(RuntimeException::new);
    }
}
