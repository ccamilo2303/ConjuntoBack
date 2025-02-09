package com.mvp.conjunto.domain.repository;

import com.mvp.conjunto.domain.entity.ConceptoEntity;
import com.mvp.conjunto.domain.entity.EstadoConjuntoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoConjuntoRepository extends JpaRepository<EstadoConjuntoEntity, Long> {

}
