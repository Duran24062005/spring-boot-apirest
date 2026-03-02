package com.spring_boot.first_spring_boot_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring_boot.first_spring_boot_app.model.SaleDetail;

public interface SaleDetailRepository extends JpaRepository<SaleDetail, Long> {
}