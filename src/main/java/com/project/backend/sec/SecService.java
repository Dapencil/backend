package com.project.backend.sec;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class SecService {
    public boolean isMyPage(Authentication authentication, Integer anotherId){
        CustomUserDetails details = (CustomUserDetails) authentication.getPrincipal();
        return details.getId()==anotherId;
    }
}
