package com.mvp.conjunto.web.rest;

import com.mvp.conjunto.service.WebService;
import com.mvp.conjunto.service.WebSolicitudesService;
import com.mvp.conjunto.web.api.ApiUtil;
import com.mvp.conjunto.web.api.WebApi;
import com.mvp.conjunto.web.api.WebSolicitudesApi;
import com.mvp.conjunto.web.api.model.Solicitud;
import com.mvp.conjunto.web.api.model.SolicitudEstadoRequest;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class WebSolicitudesController implements WebSolicitudesApi {
/*
    private final WebSolicitudesService webSolicitudesService;

    @Override
    public ResponseEntity<Solicitud> solicitudes(String c, Integer estadoSolicitud, Integer page, Integer size) {
        return ResponseEntity.ok(webSolicitudesService.solicitudes(c, estadoSolicitud, page, size));

    }

    @Override
    public ResponseEntity<Void> solicitudEstado(Integer id, String c, SolicitudEstadoRequest solicitudEstadoRequest) {
        webSolicitudesService.solicitudEstado(id, c, solicitudEstadoRequest);
        return ResponseEntity.ok().build();

    }
*/
}