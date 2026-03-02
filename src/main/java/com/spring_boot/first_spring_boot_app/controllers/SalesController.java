package com.spring_boot.first_spring_boot_app.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring_boot.first_spring_boot_app.dtos.request.SaleRequestDto;
import com.spring_boot.first_spring_boot_app.dtos.response.SaleResponseDto;
import com.spring_boot.first_spring_boot_app.service.SaleService;

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
    @GetMapping
    public List<SaleResponseDto> getSales() {
        return saleService.findAll();
    }

    @Operation(summary = "Obtener venta por id")
    @GetMapping("/{id}")
    public SaleResponseDto getSaleById(@PathVariable Long id) {
        return saleService.findById(id);
    }

    @Operation(summary = "Crear venta")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SaleResponseDto createSale(@Valid @RequestBody SaleRequestDto request) {
        return saleService.create(request);
    }

    @Operation(summary = "Actualizar venta")
    @PutMapping("/{id}")
    public SaleResponseDto updateSale(@PathVariable Long id, @Valid @RequestBody SaleRequestDto request) {
        return saleService.update(id, request);
    }

    @Operation(summary = "Eliminar venta")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSale(@PathVariable Long id) {
        saleService.delete(id);
    }
}