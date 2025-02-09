package com.mvp.conjunto.domain.repository;

import com.mvp.conjunto.domain.entity.ResidenteEntity;
import com.mvp.conjunto.domain.entity.ResidenteUnidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResidenteUnidadRepository extends JpaRepository<ResidenteUnidadEntity, Long> {



}
