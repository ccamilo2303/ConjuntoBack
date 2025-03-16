package com.mvp.conjunto.domain.repository;

import com.mvp.conjunto.domain.entity.ConceptoConjunto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ConceptoConjuntoRepository  extends JpaRepository<ConceptoConjunto, UUID>, JpaSpecificationExecutor<ConceptoConjunto> {

    List<ConceptoConjunto> findByIdConjunto_Id(UUID idConjunto);

}
