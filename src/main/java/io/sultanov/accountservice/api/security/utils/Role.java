package io.sultanov.accountservice.api.security.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public enum Role implements GrantedAuthority {

    USER("USER"),
    ACCOUNTANT("ACCOUNTANT"),
    ADMINISTRATOR("ADMINISTRATOR");

    private final String role;

    @Override
    public String getAuthority() {
        return role;
    }
}
