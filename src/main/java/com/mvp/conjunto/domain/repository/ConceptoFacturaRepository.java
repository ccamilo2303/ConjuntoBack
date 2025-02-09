package com.mvp.conjunto.domain.repository;

import com.mvp.conjunto.domain.entity.ConceptoEntity;
import com.mvp.conjunto.domain.entity.ConceptoFacturaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConceptoFacturaRepository extends JpaRepository<ConceptoFacturaEntity, Long> {

}
