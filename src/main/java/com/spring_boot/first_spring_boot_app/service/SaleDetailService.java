package com.spring_boot.first_spring_boot_app.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring_boot.first_spring_boot_app.dtos.response.SaleDetailResponseDto;
import com.spring_boot.first_spring_boot_app.exceptions.ResourceNotFoundException;
import com.spring_boot.first_spring_boot_app.model.SaleDetail;
import com.spring_boot.first_spring_boot_app.repositories.SaleDetailRepository;
import com.spring_boot.first_spring_boot_app.repositories.SaleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleDetailService {

    private final SaleDetailRepository saleDetailRepository;
    private final SaleRepository saleRepository;

    @Transactional(readOnly = true)
    public List<SaleDetailResponseDto> findAllBySaleId(Long saleId) {
        validateSaleExists(saleId);
        return saleDetailRepository.findAllBySaleId(saleId)
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public SaleDetailResponseDto findByIdAndSaleId(Long saleId, Long detailId) {
        SaleDetail detail = saleDetailRepository.findByIdAndSaleId(detailId, saleId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Detalle de venta no encontrado con id: " + detailId + " para la venta id: " + saleId));
        return toResponseDto(detail);
    }

    private void validateSaleExists(Long saleId) {
        if (!saleRepository.existsById(saleId)) {
            throw new ResourceNotFoundException("Venta no encontrada con id: " + saleId);
        }
    }

    private SaleDetailResponseDto toResponseDto(SaleDetail detail) {
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
