package com.spring_boot.first_spring_boot_app.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Detalle de producto incluido en una venta")
public class SaleDetailRequestDto {

    @NotNull(message = "El id del producto es obligatorio")
    @Schema(example = "1")
    private Long productId;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    @Schema(example = "2")
    private Integer quantity;
}