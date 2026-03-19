package com.spring_boot.first_spring_boot_app.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
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

import com.spring_boot.first_spring_boot_app.dtos.request.ProductRequestDto;
import com.spring_boot.first_spring_boot_app.dtos.response.ProductResponseDto;
import com.spring_boot.first_spring_boot_app.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Validated
@Tag(name = "Productos", description = "Endpoints para gestion de productos")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Listar productos")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Products successfully obtained"
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
    public ResponseEntity<List<ProductResponseDto>> getProducts() {
        return ResponseEntity.ok().body(productService.findAll());
    }

    @Operation(summary = "Obtener producto por id")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Product successfully obtained"
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
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok().body(productService.findById(id));
    }

    @Operation(summary = "Crear producto")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Product successfully created"
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
    public ResponseEntity<ProductResponseDto> createProduct(@Valid @RequestBody ProductRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(request));
    }

    @Operation(summary = "Actualizar producto")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Product successfully updated"
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
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequestDto request) {
        return ResponseEntity.ok().body(productService.update(id, request));
    }

    @Operation(summary = "Eliminar producto")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Product successfully deleted"
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
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
