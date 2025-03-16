package com.mvp.conjunto.domain.repository;

import com.mvp.conjunto.domain.entity.SolicitudRegistro;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
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

            return evaluate(cb, field, value, path);
        };
    }

    private static Predicate evaluate(CriteriaBuilder cb, String field, Object value, AtomicReference<Path> path) {
        if( field == null || value == null)
            return cb.conjunction();
        if(field.equals("fechaInicio"))
            return cb.greaterThanOrEqualTo(cb.function("DATE", Date.class, path.get()), castDate(value));
        if(field.equals("fechaFin"))
            return cb.lessThanOrEqualTo(cb.function("DATE", Date.class, path.get()), castDate(value));
        return cb.equal(path.get(), value);
    }

    private static Date castDate(Object value) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.parse(value.toString());
        } catch (ParseException e) {
            log.error("[castDate][value]error: {},{}", value, e.getMessage());
            return null;
        }
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
