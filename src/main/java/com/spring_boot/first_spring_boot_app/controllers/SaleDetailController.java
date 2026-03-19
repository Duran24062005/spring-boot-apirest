package com.spring_boot.first_spring_boot_app.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring_boot.first_spring_boot_app.dtos.response.SaleDetailResponseDto;
import com.spring_boot.first_spring_boot_app.service.SaleDetailService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/sales/{saleId}/details")
@RequiredArgsConstructor
@Validated
@Tag(name = "Detalle de ventas", description = "Endpoints para consultar detalles de una venta")
public class SaleDetailController {

    private final SaleDetailService saleDetailService;

    @Operation(summary = "Listar detalles de una venta")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Sale details successfully obtained"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid data or malformed request"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Authentication required"
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<SaleDetailResponseDto>> getSaleDetailsBySaleId(@PathVariable Long saleId) {
        return ResponseEntity.ok().body(saleDetailService.findAllBySaleId(saleId));
    }

    @Operation(summary = "Obtener detalle de una venta por id")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Sale detail successfully obtained"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid data or malformed request"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Authentication required"
                    )
            }
    )
    @GetMapping("/{detailId}")
    public ResponseEntity<SaleDetailResponseDto> getSaleDetailById(
            @PathVariable Long saleId,
            @PathVariable Long detailId) {
        return ResponseEntity.ok().body(saleDetailService.findByIdAndSaleId(saleId, detailId));
    }
}
