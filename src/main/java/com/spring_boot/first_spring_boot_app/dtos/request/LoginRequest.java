package com.spring_boot.first_spring_boot_app.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
    @NotBlank
    @Schema(description="Username", example="admin019")
    String username, 
    @NotBlank
    @Schema(description="Password", example="admin123!")
    String password
) {}