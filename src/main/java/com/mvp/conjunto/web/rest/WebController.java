package com.mvp.conjunto.web.rest;

import com.mvp.conjunto.service.AppService;
import com.mvp.conjunto.service.WebService;
import com.mvp.conjunto.web.api.AppApi;
import com.mvp.conjunto.web.api.WebApi;
import com.mvp.conjunto.web.api.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class WebController implements WebApi {

    private final WebService webService;


    @Override
    public ResponseEntity<Residente> residentes(String estadoSolicitud, Integer limit, Integer offset) {
        return ResponseEntity.ok(webService.residentes(estadoSolicitud, limit, offset));
    }

    @Override
    public ResponseEntity<Void> residentesIdEstado(Integer id, ResidentesIdEstadoRequest residentesIdEstadoRequest) {

        webService.residentesIdEstado(Long.valueOf(id), residentesIdEstadoRequest);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<PagoManual200Response> pagoManual(PagoManualRequest pagoManualRequest) {
        return ResponseEntity.ok(webService.pagoManual(pagoManualRequest));
    }

    @Override
    public ResponseEntity<Void> avisos(AvisosRequest avisosRequest) {
        webService.avisos(avisosRequest);
        return ResponseEntity.ok().build();

    }

    @Override
    public ResponseEntity<Void> notificaciones(NotificacionesRequest notificacionesRequest) {
        webService.notificaciones(notificacionesRequest);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<Concepto>> consultarConcepto() {
        return ResponseEntity.ok(webService.consultarConcepto());
    }

    @Override
    public ResponseEntity<Void> crearConcepto(Concepto concepto) {
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> actualizarConcepto(Integer id, Concepto concepto) {
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> eliminarConcepto(Integer id) {
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<PagosMorosos200Response> pagosMorosos(LocalDate fechaInicio, LocalDate fechaFin) {
        return WebApi.super.pagosMorosos(fechaInicio, fechaFin);
    }


}
