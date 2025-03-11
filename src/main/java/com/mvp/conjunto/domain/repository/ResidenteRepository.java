package com.mvp.conjunto.domain.repository;

import com.mvp.conjunto.domain.entity.Residente;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ResidenteRepository extends JpaRepository<Residente, UUID> , JpaSpecificationExecutor<Residente> {

  @NotNull Page<Residente> findAll(Specification specification, @NotNull Pageable pageable);

}