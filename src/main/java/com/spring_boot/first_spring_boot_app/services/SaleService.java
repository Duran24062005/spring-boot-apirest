package com.spring_boot.first_spring_boot_app.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring_boot.first_spring_boot_app.dtos.SaleDetailRequestDto;
import com.spring_boot.first_spring_boot_app.dtos.SaleDetailResponseDto;
import com.spring_boot.first_spring_boot_app.dtos.SaleRequestDto;
import com.spring_boot.first_spring_boot_app.dtos.SaleResponseDto;
import com.spring_boot.first_spring_boot_app.entities.Product;
import com.spring_boot.first_spring_boot_app.entities.Sale;
import com.spring_boot.first_spring_boot_app.entities.SaleDetail;
import com.spring_boot.first_spring_boot_app.exceptions.BadRequestException;
import com.spring_boot.first_spring_boot_app.exceptions.ResourceNotFoundException;
import com.spring_boot.first_spring_boot_app.repositories.SaleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final SaleRepository saleRepository;
    private final ProductService productService;

    @Transactional(readOnly = true)
    public List<SaleResponseDto> findAll() {
        return saleRepository.findAll().stream().map(this::toResponseDto).toList();
    }

    @Transactional(readOnly = true)
    public SaleResponseDto findById(Long id) {
        Sale sale = getSaleOrThrow(id);
        return toResponseDto(sale);
    }

    @Transactional
    public SaleResponseDto create(SaleRequestDto request) {
        Sale sale = Sale.builder()
                .customerName(request.getCustomerName())
                .description(request.getDescription())
                .build();

        List<SaleDetail> details = createDetails(sale, request.getDetails());
        sale.getSaleDetails().addAll(details);
        return toResponseDto(saleRepository.save(sale));
    }

    @Transactional
    public SaleResponseDto update(Long id, SaleRequestDto request) {
        Sale sale = getSaleOrThrow(id);
        sale.setCustomerName(request.getCustomerName());
        sale.setDescription(request.getDescription());

        restoreStock(sale.getSaleDetails());
        sale.getSaleDetails().clear();

        List<SaleDetail> newDetails = createDetails(sale, request.getDetails());
        sale.getSaleDetails().addAll(newDetails);

        return toResponseDto(saleRepository.save(sale));
    }

    @Transactional
    public void delete(Long id) {
        Sale sale = getSaleOrThrow(id);
        restoreStock(sale.getSaleDetails());
        saleRepository.delete(sale);
    }

    private Sale getSaleOrThrow(Long id) {
        return saleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venta no encontrada con id: " + id));
    }

    private List<SaleDetail> createDetails(Sale sale, List<SaleDetailRequestDto> detailRequests) {
        List<SaleDetail> details = new ArrayList<>();

        for (SaleDetailRequestDto detailRequest : detailRequests) {
            Product product = productService.getProductOrThrow(detailRequest.getProductId());

            if (product.getStock() < detailRequest.getQuantity()) {
                throw new BadRequestException("Stock insuficiente para producto id: " + product.getId());
            }

            product.setStock(product.getStock() - detailRequest.getQuantity());

            SaleDetail detail = SaleDetail.builder()
                    .sale(sale)
                    .product(product)
                    .quantity(detailRequest.getQuantity())
                    .unitPrice(product.getUnitaryPrice())
                    .build();

            details.add(detail);
        }

        return details;
    }

    private void restoreStock(List<SaleDetail> details) {
        for (SaleDetail detail : details) {
            Product product = detail.getProduct();
            product.setStock(product.getStock() + detail.getQuantity());
        }
    }

    private SaleResponseDto toResponseDto(Sale sale) {
        List<SaleDetailResponseDto> details = sale.getSaleDetails()
                .stream()
                .map(this::toDetailResponseDto)
                .toList();

        BigDecimal total = details.stream()
                .map(SaleDetailResponseDto::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return SaleResponseDto.builder()
                .id(sale.getId())
                .customerName(sale.getCustomerName())
                .description(sale.getDescription())
                .details(details)
                .total(total)
                .createdAt(sale.getCreatedAt())
                .updatedAt(sale.getUpdatedAt())
                .build();
    }

    private SaleDetailResponseDto toDetailResponseDto(SaleDetail detail) {
        BigDecimal subtotal = detail.getUnitPrice()
                .multiply(BigDecimal.valueOf(detail.getQuantity()));

        return SaleDetailResponseDto.builder()
                .id(detail.getId())
                .productId(detail.getProduct().getId())
                .productName(detail.getProduct().getName())
                .quantity(detail.getQuantity())
                .unitPrice(detail.getUnitPrice())
                .subtotal(subtotal)
                .build();
    }
}
