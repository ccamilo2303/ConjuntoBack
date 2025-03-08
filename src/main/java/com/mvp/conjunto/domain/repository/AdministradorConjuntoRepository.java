package com.mvp.conjunto.domain.repository;

import com.mvp.conjunto.domain.entity.Administrador;
import com.mvp.conjunto.domain.entity.AdministradorConjunto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RepositoryRestResource(path = "administrador-conjunto")
public interface AdministradorConjuntoRepository extends JpaRepository<AdministradorConjunto, UUID> {
}