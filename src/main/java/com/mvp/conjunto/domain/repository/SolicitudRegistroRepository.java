package com.mvp.conjunto.domain.repository;

import com.mvp.conjunto.domain.entity.SolicitudRegistro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface SolicitudRegistroRepository extends JpaRepository<SolicitudRegistro, UUID>, JpaSpecificationExecutor<SolicitudRegistro> {


//    Page<SolicitudRegistro> findByIdConjunto_IdAndIdEstado_Id(UUID idConjunto, UUID idEstado, Pageable pageable);
    Page<SolicitudRegistro> findAll(Specification<SolicitudRegistro> spec, Pageable pageable);
}