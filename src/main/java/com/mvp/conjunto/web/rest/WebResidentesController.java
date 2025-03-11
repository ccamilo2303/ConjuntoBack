package com.mvp.conjunto.web.rest;

import com.mvp.conjunto.service.WebResidentesService;
import com.mvp.conjunto.web.api.WebResidentesApi;
import com.mvp.conjunto.web.api.model.ResidenteEstadoRequest;
import com.mvp.conjunto.web.api.model.ResidenteTipoRequest;
import com.mvp.conjunto.web.api.model.ResidenteUnidad;
import com.mvp.conjunto.web.api.model.Residentes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class WebResidentesController implements WebResidentesApi {

    private final WebResidentesService webResidentesService;

    @Override
    public ResponseEntity<Residentes> residentes(Integer page, Integer size, String c) {
        return ResponseEntity.ok(webResidentesService.residentes(c, page, size));
    }

    @Override
    public ResponseEntity<ResidenteUnidad> residenteUnidad(String idResidente, String c, Integer page, Integer size) {
        return ResponseEntity.ok(webResidentesService.residenteUnidad(idResidente, c, page, size));
    }

    @Override
    public ResponseEntity<Void> residenteEstado(String id, String c, ResidenteEstadoRequest residenteEstadoRequest) {
        webResidentesService.residenteEstado(id, c, residenteEstadoRequest);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> residenteTipo(String id, String c, ResidenteTipoRequest residenteTipoRequest) {
        webResidentesService.residenteTipo(id, c, residenteTipoRequest);
        return ResponseEntity.ok().build();
    }
}
