package com.mvp.conjunto.service;

import com.mvp.conjunto.web.api.model.Solicitud;
import com.mvp.conjunto.web.api.model.SolicitudEstadoRequest;


public interface WebSolicitudesService {


    /**
     * @param c
     * @param estadoSolicitud
     * @param page
     * @param size
     * @return
     */
    Solicitud solicitudes(String c, Integer estadoSolicitud, Integer page, Integer size );

    /**
     * @param id
     * @param c
     * @param solicitudEstadoRequest
     */
    void solicitudEstado(Integer id, String c, SolicitudEstadoRequest solicitudEstadoRequest);

}
