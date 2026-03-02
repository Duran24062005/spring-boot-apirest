package com.spring_boot.first_spring_boot_app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.spring_boot.first_spring_boot_app.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Override
    @EntityGraph(attributePaths = { "saleDetails", "saleDetails.product" })
    List<Sale> findAll();

    @Override
    @EntityGraph(attributePaths = { "saleDetails", "saleDetails.product" })
    Optional<Sale> findById(Long id);
}
