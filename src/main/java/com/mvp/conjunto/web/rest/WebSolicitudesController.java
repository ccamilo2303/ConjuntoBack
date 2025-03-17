package com.mvp.conjunto.web.rest;

import com.mvp.conjunto.service.WebSolicitudesService;
import com.mvp.conjunto.web.api.WebSolicitudesApi;
import com.mvp.conjunto.web.api.model.Solicitud;
import com.mvp.conjunto.web.api.model.SolicitudEstadoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class WebSolicitudesController implements WebSolicitudesApi {

    private final WebSolicitudesService webSolicitudesService;

    @Override
    public ResponseEntity<Solicitud> solicitudes(String c, Integer page, Integer size, String estadoSolicitud) {
        return ResponseEntity.ok(webSolicitudesService.solicitudes(c, estadoSolicitud, page, size));
    }

    @Override
    public ResponseEntity<Void> solicitudEstado(String id, String c, SolicitudEstadoRequest solicitudEstadoRequest) {
        webSolicitudesService.solicitudEstado(id, c, solicitudEstadoRequest);
        return ResponseEntity.ok().build();

    }

}