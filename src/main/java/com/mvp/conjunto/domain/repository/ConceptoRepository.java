package com.mvp.conjunto.domain.repository;

import com.mvp.conjunto.domain.entity.AvisoEntity;
import com.mvp.conjunto.domain.entity.ConceptoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConceptoRepository extends JpaRepository<ConceptoEntity, Long> {

}
