package com.mvp.conjunto.domain.repository;

import com.mvp.conjunto.domain.entity.SolicitudRegistro;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

@Slf4j
public class SolicitudRegistroSpec {

    public static Specification<SolicitudRegistro> filterBy(String idEstado, String idConjunto) {

        log.info("[SolicitudRegistroSpec][filterBy] idEstado: {}, idConjunto: {}", idEstado, idConjunto);

        return Specification
                .where(hasIdEstado(idEstado))
                .and(hasIdConjunto(idConjunto));

    }

    private static Specification<SolicitudRegistro> hasIdEstado(String idEstado) {
        return (root, query, cb) -> idEstado == null ? cb.conjunction() : cb.equal(root.get("idEstado").get("id"), UUID.fromString(idEstado));
    }

    private static Specification<SolicitudRegistro> hasIdConjunto(String idConjunto) {
        return (root, query, cb) -> idConjunto == null ? cb.conjunction() : cb.equal(root.get("idConjunto").get("id"), UUID.fromString(idConjunto));
    }

}
