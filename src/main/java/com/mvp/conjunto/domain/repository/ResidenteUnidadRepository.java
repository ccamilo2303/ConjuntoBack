package com.mvp.conjunto.domain.repository;

import com.mvp.conjunto.domain.entity.ResidenteConjunto;
import com.mvp.conjunto.domain.entity.ResidenteUnidad;
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
public interface ResidenteUnidadRepository extends JpaRepository<ResidenteUnidad, UUID>, JpaSpecificationExecutor<ResidenteUnidad> {

    @NotNull Page<ResidenteUnidad> findAll(Specification specification, @NotNull Pageable pageable);

    @Query(value = "select * from residente_unidad where id_residente = :residenteId and id_unidad = :unidadId", nativeQuery = true)
    Optional<ResidenteUnidad> findByIdResidenteAndIdUnidad(UUID residenteId, UUID unidadId);

}