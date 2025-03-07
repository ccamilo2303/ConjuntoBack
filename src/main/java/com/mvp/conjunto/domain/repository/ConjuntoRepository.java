package com.mvp.conjunto.domain.repository;

import com.mvp.conjunto.domain.entity.Conjunto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ConjuntoRepository extends JpaRepository<Conjunto, UUID> {
  }