package com.mvp.conjunto.domain.repository;

import com.mvp.conjunto.domain.entity.ResidenteUnidad;
import com.mvp.conjunto.domain.entity.UnidadConcepto;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface UnidadConceptoRepository extends JpaRepository<UnidadConcepto, UUID>, JpaSpecificationExecutor<UnidadConcepto> {

    @NotNull Page<UnidadConcepto> findAll(Specification specification, @NotNull Pageable pageable);

    @Query(value = "select * from unidad_concepto where id_concepto = :conceptoId and id_unidad = :unidadId", nativeQuery = true)
    Optional<UnidadConcepto> findByIdUnidadAndIdConcepto(UUID unidadId, UUID conceptoId);
}