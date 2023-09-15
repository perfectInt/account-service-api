package io.sultanov.accountservice.domain.user;

import io.sultanov.accountservice.api.exception.ObjectAlreadyExistsException;
import io.sultanov.accountservice.api.exception.PasswordException;
import io.sultanov.accountservice.api.security.config.JwtService;
import io.sultanov.accountservice.api.security.utils.Role;
import io.sultanov.accountservice.domain.response.ChangePasswordResponse;
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
        if (authMapper.selectUserByEmail(user.getEmail()).isPresent())
            throw new ObjectAlreadyExistsException("User with this email already exists");
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

    public ChangePasswordResponse changePassword(ChangePasswordRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = authMapper.selectUserByEmail(authentication.getName()).orElseThrow();
        if (!passwordEncoder.matches(request.getOldPassword(), userDetails.getPassword()))
            throw new PasswordException("Passwords are not matching");
        if (passwordEncoder.matches(request.getNewPassword(), userDetails.getPassword()))
            throw new PasswordException("Passwords must be different!");
        request.setNewPassword(passwordEncoder.encode(request.getNewPassword()));
        userMapper.updatePassword(authentication.getName(), request);
        return new ChangePasswordResponse(authentication.getName(), "The password has been updated successfully");
    }
}
