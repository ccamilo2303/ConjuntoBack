package com.mvp.conjunto.domain.repository;

import com.mvp.conjunto.domain.entity.CuentaCobroUnidad;
import com.mvp.conjunto.domain.entity.UnidadConcepto;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface CuentaCobroUnidadRepository extends JpaRepository<CuentaCobroUnidad, UUID>, JpaSpecificationExecutor<CuentaCobroUnidad> {

    @NotNull Page<CuentaCobroUnidad> findAll(Specification specification, @NotNull Pageable pageable);

    @Query(value = "select * from cuenta_cobro_unidad where fecha_inicio = :fechaInicio and fecha_fin = :fechaFin and id_unidad in :idUnidad", nativeQuery = true)
    Optional<List<CuentaCobroUnidad>> findByFechaInicioAndFechaFinAndUnidadIds(Instant fechaInicio, Instant fechaFin, List<UUID> idUnidad);

    @Query(value = "select * from cuenta_cobro_unidad where fecha_inicio = :fechaInicio and fecha_fin = :fechaFin and id_unidad = :idUnidad", nativeQuery = true)
    Optional<CuentaCobroUnidad> findByFechaInicioAndFechaFinAndUnidadId(Instant fechaInicio, Instant fechaFin, UUID idUnidad);

}