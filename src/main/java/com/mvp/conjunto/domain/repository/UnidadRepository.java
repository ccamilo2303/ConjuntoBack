package com.mvp.conjunto.domain.repository;

import com.mvp.conjunto.domain.entity.ConjuntoEntity;
import com.mvp.conjunto.domain.entity.UnidadConceptoEntity;
import com.mvp.conjunto.domain.entity.UnidadEntity;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnidadRepository extends JpaRepository<UnidadEntity, Long> {

    public Optional<UnidadEntity> findByInteriorAndAptoAndIdConjunto(Integer interior, Integer apto, ConjuntoEntity idConjunto);

}
