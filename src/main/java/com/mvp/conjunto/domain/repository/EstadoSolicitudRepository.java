package com.mvp.conjunto.domain.repository;

import com.mvp.conjunto.domain.entity.EstadoResidenteUnidadEntity;
import com.mvp.conjunto.domain.entity.EstadoSolicitudEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoSolicitudRepository extends JpaRepository<EstadoSolicitudEntity, Long> {

    EstadoSolicitudEntity findByEstado(String estado);

}
