package com.project.backend.models.ResponseModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
@Getter
@AllArgsConstructor
public class AuthResponse {

    private final String jwt;
    private Collection<? extends GrantedAuthority> role;
}
