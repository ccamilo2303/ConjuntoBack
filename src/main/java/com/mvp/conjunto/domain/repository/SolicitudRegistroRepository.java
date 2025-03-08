package com.mvp.conjunto.domain.repository;

import com.mvp.conjunto.domain.entity.SolicitudRegistro;
import com.mvp.conjunto.domain.model.SolicitudRegistroDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
@RepositoryRestResource(path = "solicitud")
public interface SolicitudRegistroRepository extends JpaRepository<SolicitudRegistro, UUID> {

    @Query("SELECT new com.mvp.conjunto.domain.model.SolicitudRegistroDTO(c) FROM SolicitudRegistro c")
    List<SolicitudRegistroDTO> findAllAsDTO();

}