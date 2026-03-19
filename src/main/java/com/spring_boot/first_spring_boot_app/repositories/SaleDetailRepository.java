package com.spring_boot.first_spring_boot_app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.spring_boot.first_spring_boot_app.model.SaleDetail;

public interface SaleDetailRepository extends JpaRepository<SaleDetail, Long> {

    @EntityGraph(attributePaths = { "product", "sale" })
    List<SaleDetail> findAllBySaleId(Long saleId);

    @EntityGraph(attributePaths = { "product", "sale" })
    Optional<SaleDetail> findByIdAndSaleId(Long id, Long saleId);
}
