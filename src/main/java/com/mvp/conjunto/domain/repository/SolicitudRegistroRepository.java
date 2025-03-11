package com.mvp.conjunto.domain.repository;

import com.mvp.conjunto.domain.entity.SolicitudRegistro;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface SolicitudRegistroRepository extends JpaRepository<SolicitudRegistro, UUID>, JpaSpecificationExecutor<SolicitudRegistro> {

    @NotNull Page<SolicitudRegistro> findAll(Specification<SolicitudRegistro> spec, @NotNull Pageable pageable);
}