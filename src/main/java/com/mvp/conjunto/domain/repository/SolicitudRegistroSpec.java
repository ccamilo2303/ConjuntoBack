package com.mvp.conjunto.domain.repository;

import com.mvp.conjunto.domain.entity.SolicitudRegistro;
import jakarta.persistence.criteria.Path;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class SolicitudRegistroSpec {

    public static Specification<SolicitudRegistro> filterBy(String idEstado, String idConjunto) {

        log.info("[SolicitudRegistroSpec][filterBy] idEstado: {}, idConjunto: {}", idEstado, idConjunto);

        return Specification
                .where(hasIdEstado(idEstado))
                .and(hasIdConjunto(idConjunto));

    }

    public static Specification<?> filterBy(Map<String, Object> filtros) {

        log.info("[SolicitudRegistroSpec][filterBy] filtros: {}", filtros);

        AtomicReference<Specification>specification = new AtomicReference<>();

        filtros.forEach((key, value) -> {
            if(specification.get() == null){
                specification.set(Specification.where(hasField(key, value)));
            }else{
                specification.set(specification.get().and(hasField(key, value)));
            }
        });

        return (root, query, builder) -> {
            query.orderBy(builder.desc(root.get("fechaCreacion")));
            return  specification.get().toPredicate(root, query, builder);
        };

    }



    private static Specification<?> hasField(String field, Object value) {

        return (root, query, cb) -> {
            AtomicReference<Path> path = new AtomicReference<>();
            Arrays.stream(field.split("\\.")).forEach(x -> {
                if(path.get() == null){
                    path.set(root.get(x));
                }else{
                    path.set(path.get().get(x));
                }
            });

            return field == null || value == null ? cb.conjunction() : cb.equal(path.get(), value);
        };
    }
    private static Specification<?> hasIdUnidadConjunto(String idConjunto) {
        return (root, query, cb) -> idConjunto == null ? cb.conjunction() : cb.equal(root.get("idUnidad").get("idConjunto").get("id"), UUID.fromString(idConjunto));
    }
    private static Specification<SolicitudRegistro> hasIdEstado(String idEstado) {
        return (root, query, cb) -> idEstado == null ? cb.conjunction() : cb.equal(root.get("idEstado").get("id"), UUID.fromString(idEstado));
    }

    private static Specification<SolicitudRegistro> hasIdConjunto(String idConjunto) {
        return (root, query, cb) -> idConjunto == null ? cb.conjunction() : cb.equal(root.get("idConjunto").get("id"), UUID.fromString(idConjunto));
    }

}
