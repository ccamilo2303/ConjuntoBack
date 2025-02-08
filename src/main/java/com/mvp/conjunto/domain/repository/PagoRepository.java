package com.mvp.conjunto.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PagoRepository extends JpaRepository<PagoEntity, Long> {

    @Query(value = "SELECT * FROM pagos WHERE id_residente = :idResidente", nativeQuery = true)
    Optional<List<PagoEntity>> findByIdResidente(@Param("idResidente") Long idResidente);
}
