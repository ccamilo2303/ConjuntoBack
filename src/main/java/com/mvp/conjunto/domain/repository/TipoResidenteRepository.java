package com.mvp.conjunto.domain.repository;

import com.mvp.conjunto.domain.entity.SolicitudEntity;
import com.mvp.conjunto.domain.entity.TipoResidenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface TipoResidenteRepository extends JpaRepository<TipoResidenteEntity, Long> {

    Optional<TipoResidenteEntity> findByTipo(String tipo);


}
