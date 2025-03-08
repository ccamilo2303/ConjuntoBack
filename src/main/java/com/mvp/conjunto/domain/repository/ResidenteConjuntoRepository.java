package com.mvp.conjunto.domain.repository;

import com.mvp.conjunto.domain.entity.Residente;
import com.mvp.conjunto.domain.entity.ResidenteConjunto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RepositoryRestResource(path = "residente-conjunto")
public interface ResidenteConjuntoRepository extends JpaRepository<ResidenteConjunto, UUID> {
  }