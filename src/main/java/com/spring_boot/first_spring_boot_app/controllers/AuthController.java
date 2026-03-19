package com.spring_boot.first_spring_boot_app.controllers;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring_boot.first_spring_boot_app.dtos.request.LoginRequest;
import com.spring_boot.first_spring_boot_app.exceptions.BusinessRuleException;
import com.spring_boot.first_spring_boot_app.security.JwtService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name="Auth", description="Authentication and profile endpoints.")
public class AuthController {
    
    private final JwtService jwtService;


    @PostMapping("/login")
    @Operation(summary = "Iniciar sesion", description = "Genera un token JWT para acceder a los endpoints protegidos", security = {})
    public Map<String, String> postMethodName(@RequestBody LoginRequest request) {
        if (request.username().equals("admin019") && request.password().equals("admin123!")) {
            String token = jwtService.generateToken(request.username());
            return Map.of("token", token);
        }
        
        throw new BusinessRuleException("Invalid Credentials");
    }
    
}
