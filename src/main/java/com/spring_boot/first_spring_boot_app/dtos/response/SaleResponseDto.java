package com.spring_boot.first_spring_boot_app.dtos.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "Respuesta con datos de una venta")
public class SaleResponseDto {

    private Long id;
    private String customerName;
    private String description;
    private List<SaleDetailResponseDto> details;
    private BigDecimal total;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}