package com.mvp.conjunto.domain.model;

import com.mvp.conjunto.domain.entity.SolicitudRegistro;

import java.util.UUID;

public record SolicitudRegistroDTO(UUID id, String nombre, String email, String telefono, String tipo, String estado, String descripcion, String comentario, String fechaCreacion) {

    public SolicitudRegistroDTO(SolicitudRegistro solicitudRegistro){
        this(solicitudRegistro.getId(), solicitudRegistro.getIdResidente().getNombre(), solicitudRegistro.getIdResidente().getEmail(), solicitudRegistro.getIdResidente().getTelefono(), "", solicitudRegistro.getIdEstado().getNombre(), solicitudRegistro.getDescripcion(), solicitudRegistro.getComentario(), solicitudRegistro.getFechaCreacion().toString());
    }

}
