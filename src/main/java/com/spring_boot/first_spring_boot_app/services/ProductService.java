package com.spring_boot.first_spring_boot_app.services;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring_boot.first_spring_boot_app.dtos.ProductRequestDto;
import com.spring_boot.first_spring_boot_app.dtos.ProductResponseDto;
import com.spring_boot.first_spring_boot_app.entities.Product;
import com.spring_boot.first_spring_boot_app.exceptions.BadRequestException;
import com.spring_boot.first_spring_boot_app.exceptions.ResourceNotFoundException;
import com.spring_boot.first_spring_boot_app.repositories.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<ProductResponseDto> findAll() {
        return productRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public ProductResponseDto findById(Long id) {
        Product product = getProductOrThrow(id);
        return toResponseDto(product);
    }

    @Transactional
    public ProductResponseDto create(ProductRequestDto request) {
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .unitaryPrice(request.getUnitaryPrice())
                .stock(request.getStock())
                .build();

        return toResponseDto(productRepository.save(product));
    }

    @Transactional
    public ProductResponseDto update(Long id, ProductRequestDto request) {
        Product product = getProductOrThrow(id);
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setUnitaryPrice(request.getUnitaryPrice());
        product.setStock(request.getStock());
        return toResponseDto(productRepository.save(product));
    }

    @Transactional
    public void delete(Long id) {
        Product product = getProductOrThrow(id);
        try {
            productRepository.delete(product);
        } catch (DataIntegrityViolationException ex) {
            throw new BadRequestException("No se puede eliminar el producto porque tiene ventas asociadas");
        }
    }

    public Product getProductOrThrow(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id: " + id));
    }

    private ProductResponseDto toResponseDto(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .unitaryPrice(product.getUnitaryPrice())
                .stock(product.getStock())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }
}
