package com.mvp.conjunto.domain.repository;

import com.mvp.conjunto.domain.entity.EstadoSolicitudEntity;
import com.mvp.conjunto.domain.entity.ResidenteUnidadEntity;
import com.mvp.conjunto.domain.entity.SolicitudEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SolicitudRepository extends JpaRepository<SolicitudEntity, Long> {


    Optional<List<SolicitudEntity>> findByIdEstado(EstadoSolicitudEntity idEstado);

}
