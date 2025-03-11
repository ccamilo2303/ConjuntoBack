package com.mvp.conjunto.domain.repository;

import com.mvp.conjunto.domain.entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface PagoRepository extends JpaRepository<Pago, UUID>, JpaSpecificationExecutor<Pago> {
  }