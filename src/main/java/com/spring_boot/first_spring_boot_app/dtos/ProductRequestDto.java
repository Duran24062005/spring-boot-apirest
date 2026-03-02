package com.spring_boot.first_spring_boot_app.dtos;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Datos para crear o actualizar un producto")
public class ProductRequestDto {

    @NotBlank(message = "El nombre es obligatorio")
    @Schema(example = "Laptop Lenovo")
    private String name;

    @Schema(example = "Laptop 16GB RAM")
    private String description;

    @NotNull(message = "El precio unitario es obligatorio")
    @PositiveOrZero(message = "El precio unitario no puede ser negativo")
    @Schema(example = "3200.00")
    private BigDecimal unitaryPrice;

    @NotNull(message = "El stock es obligatorio")
    @PositiveOrZero(message = "El stock no puede ser negativo")
    @Schema(example = "10")
    private Integer stock;
}
