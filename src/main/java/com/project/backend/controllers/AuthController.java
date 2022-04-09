package com.project.backend.controllers;

import com.project.backend.Util.JWTUtil;
import com.project.backend.models.ResponseModel.AuthRequest;
import com.project.backend.models.ResponseModel.AuthResponse;
import com.project.backend.sec.CustomUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("/auth/{code}")
    public String test(@PathVariable String code){
        return passwordEncoder.encode(code);
    }

    @PostMapping("/auth")
    @ResponseBody
    public ResponseEntity createAuthToken(@RequestBody AuthRequest request){
        try{
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword())
            );
        }catch (Exception e){
            return new ResponseEntity("incorrect username or password", HttpStatus.BAD_REQUEST);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(jwt,userDetails.getAuthorities()));
    }
}
