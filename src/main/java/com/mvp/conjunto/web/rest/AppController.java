package com.mvp.conjunto.web.rest;

import com.mvp.conjunto.web.api.AppApi;
import com.mvp.conjunto.web.api.model.ConjuntosRes;
import com.mvp.conjunto.web.api.model.HistorialAvisosResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class AppController implements AppApi {


    @Override
    public ResponseEntity<HistorialAvisosResponse> avisosHistorial(LocalDate fechaInicio, LocalDate fechaFin, Integer limit, Integer offset) {
        return null;
    }

    @Override
    public ResponseEntity<ConjuntosRes> conjuntos(String nombre, String ubicacion, String estado, Integer limit, Integer offset) {
        return null;
    }
}
