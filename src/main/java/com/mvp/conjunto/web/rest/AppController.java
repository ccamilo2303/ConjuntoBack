package com.mvp.conjunto.web.rest;

import com.mvp.conjunto.service.AppService;
import com.mvp.conjunto.service.impl.AuthService;
import com.mvp.conjunto.web.api.AppApi;
import com.mvp.conjunto.web.api.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class AppController implements AppApi {

    private final AppService appService;


    @Override
    public ResponseEntity<List<FacturaResponse>> residentesFacturas() {
        return ResponseEntity.ok(appService.residentesFacturas());
    }

    @Override
    public ResponseEntity<ResidenteRegistroResponse> residentesRegistro(ResidenteRegistroRequest residenteRegistroRequest) {
        return ResponseEntity.ok(appService.residentesRegistro(residenteRegistroRequest));
    }

    @Override
    public ResponseEntity<UsuariosEstadoCuenta200Response> usuariosEstadoCuenta() {
        return ResponseEntity.ok(appService.usuariosEstadoCuenta());
    }

    @Override
    public ResponseEntity<UsuariosHistorialPagos200Response> usuariosHistorialPagos(LocalDate fechaInicio, LocalDate fechaFin, Integer limit, Integer offset) {
        return ResponseEntity.ok(appService.usuariosHistorialPagos(fechaInicio, fechaFin, limit, offset));
    }

    @Override
    public ResponseEntity<HistorialAvisosResponse> avisosHistorial(LocalDate fechaInicio, LocalDate fechaFin, Integer limit, Integer offset) {
        return ResponseEntity.ok(appService.avisosHistorial(fechaInicio, fechaFin, limit, offset));
    }

    @Override
    public ResponseEntity<ConjuntosRes> conjuntos(String nombre, String ubicacion, String estado, Integer limit, Integer offset) {
        return ResponseEntity.ok(appService.conjuntos(nombre, ubicacion, estado, limit, offset));
    }
}
