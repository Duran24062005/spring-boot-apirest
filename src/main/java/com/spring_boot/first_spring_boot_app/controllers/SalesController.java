package com.spring_boot.first_spring_boot_app.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;



@RestController
public class SalesController {

    @GetMapping("/api/sales")
    public String getSales() {
        return "Hola mundo";
    }
    
    @GetMapping("/api/sales/{id}")
    public String getSaleById() {
        return "Hola mundo";
    }

    @Operation(description="Crea un nuevo registro de ventas")
    @PostMapping("/api/sales/create")
    public String createSale(@RequestBody String entity) {
        return entity;
    }
    
    @PutMapping("/api/sales")
    public String updateSale() {
        return "Hola mundo";
    }

    @DeleteMapping("/api/sales")
    public String deleteSale() {
        return "Hola mundo";
    }
    
}
