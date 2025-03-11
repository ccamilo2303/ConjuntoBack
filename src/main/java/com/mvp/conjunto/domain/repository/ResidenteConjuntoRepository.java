package com.mvp.conjunto.domain.repository;

import com.mvp.conjunto.domain.entity.ResidenteConjunto;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ResidenteConjuntoRepository extends JpaRepository<ResidenteConjunto, UUID>, JpaSpecificationExecutor<ResidenteConjunto> {

  @NotNull Page<ResidenteConjunto> findAll(Specification specification, @NotNull Pageable pageable);
  List<ResidenteConjunto> findAll(Specification specification);

  @Query(value = "select * from residente_conjunto where id_conjunto = :idConjunto and id_residente = :idResidente", nativeQuery = true)
  Optional<ResidenteConjunto> findByIdConjuntoAndIdResidente(UUID idConjunto, UUID idResidente);

}