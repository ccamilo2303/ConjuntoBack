package com.mvp.conjunto.service.impl.mapper;

import com.mvp.conjunto.web.api.model.Concepto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;

@Mapper(componentModel = "spring")
public interface ConceptosMapper extends DateMapper{

    @Mapping(target = "fechaRegistro", expression = "java(mapDate(fechaRegistro))")
    public Concepto mapConcepto(com.mvp.conjunto.domain.entity.Concepto concepto, Instant fechaRegistro);

}
