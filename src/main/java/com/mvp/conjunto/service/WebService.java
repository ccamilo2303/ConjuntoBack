package com.mvp.conjunto.service;

import com.mvp.conjunto.web.api.WebApi;
import com.mvp.conjunto.web.api.model.*;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface WebService {

    Residente residentes(String estadoSolicitud, Integer limit, Integer offset);
    void residentesIdEstado(Long id, ResidentesIdEstadoRequest residentesIdEstadoRequest);
    PagoManual200Response pagoManual(PagoManualRequest pagoManualRequest);
    void avisos(AvisosRequest avisosRequest);
    void notificaciones(NotificacionesRequest notificacionesRequest);
    List<Concepto> consultarConcepto();
}
