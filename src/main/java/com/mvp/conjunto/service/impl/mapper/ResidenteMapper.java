package com.mvp.conjunto.service.impl.mapper;

import com.mvp.conjunto.web.api.model.Residente;
import com.mvp.conjunto.web.api.model.Unidad;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ResidenteMapper extends DateMapper{

    @Mapping(source = "id", target = "idResidenteUnidad")
    @Mapping(source = "idTipo.tipo", target = "tipo")
    @Mapping(source = "idEstado.nombre", target = "estado")
    @Mapping(target = "fechaRegistro", expression = "java(mapDate(residente.getFechaCreacion()))")
    public Residente mapResidente(com.mvp.conjunto.domain.entity.Residente residente);


    @Mapping(source = "idEstado.nombre", target = "estado")
    public Unidad mapUnidad(com.mvp.conjunto.domain.entity.Unidad unidad);

}
