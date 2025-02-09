package com.mvp.conjunto.domain.repository;

import com.mvp.conjunto.domain.entity.TipoResidenteEntity;
import com.mvp.conjunto.domain.entity.UnidadConceptoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadConceptoRepository extends JpaRepository<UnidadConceptoEntity, Long> {



}
