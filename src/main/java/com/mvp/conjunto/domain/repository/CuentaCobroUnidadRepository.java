package com.mvp.conjunto.domain.repository;

import com.mvp.conjunto.domain.entity.CuentaCobroUnidad;
import com.mvp.conjunto.domain.entity.UnidadConcepto;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface CuentaCobroUnidadRepository extends JpaRepository<CuentaCobroUnidad, UUID>, JpaSpecificationExecutor<CuentaCobroUnidad> {

    @NotNull Page<CuentaCobroUnidad> findAll(Specification specification, @NotNull Pageable pageable);

}