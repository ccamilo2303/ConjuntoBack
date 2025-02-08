package com.mvp.conjunto.domain.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConjuntoRepository extends JpaRepository<ConjuntoEntity, Long> {

    Page<ConjuntoEntity> findAll(@NotNull Pageable pageable);
}
