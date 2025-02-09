package com.mvp.conjunto.domain.repository;

import com.mvp.conjunto.domain.entity.EstadoConjuntoEntity;
import com.mvp.conjunto.domain.entity.EstadoFacturaUnidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoFacturaRepository extends JpaRepository<EstadoFacturaUnidadEntity, Long> {

}
