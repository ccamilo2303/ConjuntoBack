package com.mvp.conjunto.domain.repository;

import com.mvp.conjunto.domain.entity.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RepositoryRestResource(path = "administrador")
public interface AdministradorRepository extends JpaRepository<Administrador, UUID> {
}