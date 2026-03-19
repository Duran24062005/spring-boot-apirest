package com.spring_boot.first_spring_boot_app.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring_boot.first_spring_boot_app.dtos.request.SaleRequestDto;
import com.spring_boot.first_spring_boot_app.dtos.response.SaleResponseDto;
import com.spring_boot.first_spring_boot_app.service.SaleService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
@Validated
@Tag(name = "Ventas", description = "Endpoints para gestion de ventas")
public class SalesController {

    private final SaleService saleService;

    @Operation(summary = "Listar ventas")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Sales successfully obtained"
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
    public ResponseEntity<List<SaleResponseDto>> getSales() {
        return ResponseEntity.ok().body(saleService.findAll());
    }

    @Operation(summary = "Obtener venta por id")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Sale successfully obtained"
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
    @GetMapping("/{id}")
    public ResponseEntity<SaleResponseDto> getSaleById(@PathVariable Long id) {
        return ResponseEntity.ok().body(saleService.findById(id));
    }

    @Operation(summary = "Crear venta")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Sale successfully created"
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
    @PostMapping
    public ResponseEntity<SaleResponseDto> createSale(@Valid @RequestBody SaleRequestDto request) {
        return ResponseEntity.status(201).body(saleService.create(request));
    }

    @Operation(summary = "Actualizar venta")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Sale successfully updated"
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
    @PutMapping("/{id}")
    public ResponseEntity<SaleResponseDto> updateSale(@PathVariable Long id, @Valid @RequestBody SaleRequestDto request) {
        return ResponseEntity.ok().body(saleService.update(id, request));
    }

    @Operation(summary = "Eliminar venta")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Sale successfully deleted"
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
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id) {
        saleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
