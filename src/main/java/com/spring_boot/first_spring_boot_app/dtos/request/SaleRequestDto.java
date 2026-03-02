package com.spring_boot.first_spring_boot_app.dtos.request;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Datos para crear o actualizar una venta")
public class SaleRequestDto {

    @NotBlank(message = "El nombre del cliente es obligatorio")
    @Schema(example = "Juan Perez")
    private String customerName;

    @Schema(example = "Compra de equipos")
    private String description;

    @Valid
    @NotEmpty(message = "La venta debe tener al menos un detalle")
    private List<SaleDetailRequestDto> details;
}