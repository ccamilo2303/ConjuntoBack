package com.mvp.conjunto.domain.repository;

import com.mvp.conjunto.domain.entity.MetodoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface MetodoPagoRepository extends JpaRepository<MetodoPago, UUID> {
}