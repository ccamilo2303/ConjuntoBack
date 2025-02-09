package com.mvp.conjunto.domain.repository;

import com.mvp.conjunto.domain.entity.EstadoFacturaUnidadEntity;
import com.mvp.conjunto.domain.entity.EstadoResidenteEntity;
import com.mvp.conjunto.domain.entity.EstadoResidenteUnidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoResidenteRepository extends JpaRepository<EstadoResidenteEntity, Long> {

}
