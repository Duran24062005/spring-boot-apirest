package com.spring_boot.first_spring_boot_app.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class ProductController {
    @GetMapping("/api/products")
    public String getSales() {
        return "Hola mundo";
    }
    
    @GetMapping("/api/products/{id}")
    public String getSaleById() {
        return "Hola mundo";
    }

    @Operation(description="Crea un nuevo registro de ventas")
    @PostMapping("/api/products/create")
    public String createSale() {
        return "";
    }
    
    @PutMapping("/api/products")
    public String updateSale() {
        return "Hola mundo";
    }

    @DeleteMapping("/api/products")
    public String deleteSale() {
        return "Hola mundo";
    }
}
