package com.project.backend.models.ResponseModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@Getter
@AllArgsConstructor
public class AuthResponse {

    private final String jwt;
    private List<String> role;
}
