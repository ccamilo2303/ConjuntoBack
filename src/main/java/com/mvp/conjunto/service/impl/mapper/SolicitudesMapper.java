package com.mvp.conjunto.service.impl.mapper;

import com.mvp.conjunto.domain.entity.SolicitudRegistro;
import com.mvp.conjunto.web.api.model.SolicitudSolicitudesInner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SolicitudesMapper extends DateMapper{

    @Mapping(source = "id", target = "id")
    @Mapping(source = "idResidente.nombre", target = "nombre")
    @Mapping(source = "descripcion", target = "descripcion")
    @Mapping(source = "comentario", target = "comentario")
    @Mapping(source = "idResidente.email", target = "email")
    @Mapping(source = "idResidente.telefono", target = "telefono")
    @Mapping(source = "idResidente.idTipo.tipo", target = "tipo")
    @Mapping(source = "idEstado.nombre", target = "estado")
    @Mapping(target = "fechaCreacion", expression = "java(mapDate(solicitudRegistro.getFechaActualizacion()))")
    SolicitudSolicitudesInner mapSolicitud(SolicitudRegistro solicitudRegistro);

}
