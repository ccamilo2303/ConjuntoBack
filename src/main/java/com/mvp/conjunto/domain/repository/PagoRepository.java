package com.mvp.conjunto.domain.repository;

import com.mvp.conjunto.domain.entity.FacturaUnidadEntity;
import com.mvp.conjunto.domain.entity.PagoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PagoRepository extends JpaRepository<PagoEntity, Long> {


    Optional<List<PagoEntity>> findByIdFacturaUnidad(FacturaUnidadEntity idFacturaUnidad);
}
