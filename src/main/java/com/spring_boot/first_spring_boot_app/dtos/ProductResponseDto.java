package com.spring_boot.first_spring_boot_app.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "Respuesta con datos de producto")
public class ProductResponseDto {

    private Long id;
    private String name;
    private String description;
    private BigDecimal unitaryPrice;
    private Integer stock;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
