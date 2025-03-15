package com.mvp.conjunto.service.impl.mapper;

import com.mvp.conjunto.domain.entity.ConceptoCuentaCobro;
import com.mvp.conjunto.domain.entity.CuentaCobroUnidad;
import com.mvp.conjunto.web.api.model.Concepto;
import com.mvp.conjunto.web.api.model.CuentaCobro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface CuentaCobroMapper extends DateMapper{

    @Mapping(source = "idEstado.nombre", target = "estado")
    @Mapping(target = "fechaInicio", expression = "java(mapDate(cuentaCobro.getFechaInicio()))")
    @Mapping(target = "fechaFin", expression = "java(mapDate(cuentaCobro.getFechaFin()))")
    @Mapping(target = "conceptos", expression = "java(mapConcepto(cuentaCobro.getConceptoCuentaCobros()))")
    @Mapping(target = "fechaRegistro", expression = "java(mapDate(cuentaCobro.getFechaCreacion()))")
    @Mapping(source = "idUnidad", target = "unidad")
    CuentaCobro mapCuentaCobro(CuentaCobroUnidad cuentaCobro);

    default List<Concepto> mapConcepto(Set<ConceptoCuentaCobro> conceptosCuentaCobro){

        return conceptosCuentaCobro.stream().map(x->{
            Concepto concepto = new Concepto();
            concepto.setId(x.getId().toString());
            concepto.setNombre(x.getIdConcepto().getNombre());
            concepto.setFechaRegistro(mapDate(x.getFechaCreacion()));
            concepto.setValor(x.getValor().doubleValue());
            return concepto;

        }).toList();


    }

}
